package com.hnv99.delivery.reservation.application.service

import com.hnv99.delivery.reservation.application.port.input.RejectReservationUseCase
import com.hnv99.delivery.reservation.application.port.output.LoadPaymentPort
import com.hnv99.delivery.reservation.application.port.output.SendRejectedReservationMessagePort
import com.hnv99.delivery.reservation.domain.payment.PaymentGatewayService
import com.hnv99.delivery.reservation.domain.payment.service.CancelPaymentService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional(readOnly = true)
class RejectReservationService(
    private val loadPaymentPort: LoadPaymentPort,
    private val sendRejectedReservationMessagePort: SendRejectedReservationMessagePort,
    paymentGatewayService: PaymentGatewayService
) : RejectReservationUseCase {

    private val cancelPaymentService = CancelPaymentService(paymentGatewayService)

    @Transactional
    override fun reject(reservationId: UUID) {
        val payment = loadPaymentPort.loadPaymentByReservationId(reservationId)
        cancelPaymentService.cancel(payment)
        sendRejectedReservationMessagePort.sendRejectedReservationMessage()
    }
}