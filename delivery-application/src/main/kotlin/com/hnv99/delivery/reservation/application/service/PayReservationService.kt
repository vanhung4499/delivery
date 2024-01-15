package com.hnv99.delivery.reservation.application.service

import com.hnv99.delivery.common.models.Money
import com.hnv99.delivery.reservation.application.port.input.PayReservationRequest
import com.hnv99.delivery.reservation.application.port.input.PayReservationUseCase
import com.hnv99.delivery.reservation.application.port.output.LoadReservationPort
import com.hnv99.delivery.reservation.application.port.output.SavePaymentPort
import com.hnv99.delivery.reservation.application.port.output.SendNewReservationMessagePort
import com.hnv99.delivery.reservation.domain.payment.Payment
import com.hnv99.delivery.reservation.domain.payment.PaymentGatewayService
import com.hnv99.delivery.reservation.domain.payment.service.ConfirmPaymentService
import com.hnv99.delivery.reservation.domain.reservation.Reservation
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
@Transactional(readOnly = true)
class PayReservationService(
    private val loadReservationPort: LoadReservationPort,
    private val savePaymentPort: SavePaymentPort,
    private val sendNewReservationMessagePort: SendNewReservationMessagePort,
    paymentGatewayService: PaymentGatewayService
) : PayReservationUseCase {

    private val confirmPaymentService = ConfirmPaymentService(paymentGatewayService)

    @Transactional
    override fun pay(payReservationRequest: PayReservationRequest): UUID {
        val reservation = loadReservationPort.loadReservationById(payReservationRequest.reservationId)
        val payment = createPayment(payReservationRequest, reservation)
        val savedId = savePaymentPort.save(payment)

        confirmPaymentService.confirm(payment)

        sendNewReservationMessagePort
            .sendNewReservationMessage(reservation.id, reservation.buildDescription())

        return savedId
    }

    private fun createPayment(payReservationRequest: PayReservationRequest, reservation: Reservation) =
        Payment.of(payReservationRequest.paymentKey, reservation, Money.from(payReservationRequest.amount))
}
