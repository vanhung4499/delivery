package com.hnv99.delivery.reservation.persistence

import com.hnv99.delivery.common.exception.EntityNotFoundException
import com.hnv99.delivery.reservation.application.port.input.ReservationResponse
import com.hnv99.delivery.reservation.application.port.input.ReservedProduct
import com.hnv99.delivery.reservation.application.port.output.FindPaymentPort
import com.hnv99.delivery.reservation.application.port.output.FindReservationPort
import com.hnv99.delivery.reservation.application.port.output.SavePaymentPort
import com.hnv99.delivery.reservation.application.port.output.SaveReservationPort
import com.hnv99.delivery.reservation.domain.payment.Payment
import com.hnv99.delivery.reservation.domain.reservation.Reservation
import org.springframework.stereotype.Repository
import java.util.UUID

private const val NOT_FOUND_RESERVATION_MESSAGE = "Reservation information not found."
private const val NOT_FOUND_PAYMENT_MESSAGE = "This payment does not exist."
@Repository
class JpaReservationPersistenceAdapter(
    private val reservationRepository: ReservationRepository,
    private val paymentRepository: PaymentRepository,
) : SaveReservationPort, SavePaymentPort, FindReservationPort, FindPaymentPort {
    override fun save(reservation: Reservation): UUID {
        reservationRepository.save(reservation)
        return reservation.id
    }

    override fun save(payment: Payment): UUID {
        paymentRepository.save(payment)

        return payment.id
    }

    override fun findReservationById(reservationId: UUID): Reservation {
        return reservationRepository.findById(reservationId)
            .orElseThrow { throw EntityNotFoundException(NOT_FOUND_RESERVATION_MESSAGE) }
    }

    override fun findPaymentByReservationId(reservationId: UUID): Payment {
        return paymentRepository.findByReservationId(reservationId)
            .orElseThrow { throw EntityNotFoundException(NOT_FOUND_PAYMENT_MESSAGE) }
    }

    override fun queryReservationById(reservationId: UUID): ReservationResponse {
        val reservation = reservationRepository.findWithShopById(reservationId)
            .orElseThrow { throw EntityNotFoundException(NOT_FOUND_RESERVATION_MESSAGE) }

        return mapToReservationResponse(reservation)
    }

    override fun findAllReservationByShopId(shopId: UUID): List<ReservationResponse> {
        val reservations = reservationRepository.findAllByShopId(shopId)

        return reservations.map { mapToReservationResponse(it) }
    }

    private fun mapToReservationResponse(reservation: Reservation) =
        ReservationResponse(
            reservation.id,
            reservation.shop.title.title,
            reservation.buildDescription(),
            reservation.reserveAt,
            reservation.reservationStatus.name,
            reservation.lineItems.map {
                ReservedProduct(
                    it.itemId.id,
                    it.name,
                    it.price.value,
                    it.quantity
                )
            }
        )
}