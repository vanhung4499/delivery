package com.hnv99.delivery.reservation.application.port.output

import com.hnv99.delivery.reservation.application.port.input.ReservationResponse
import com.hnv99.delivery.reservation.domain.reservation.Reservation
import java.util.*

interface FindReservationPort {
    fun findReservationById(reservationId: UUID): Reservation

    fun queryReservationById(reservationId: UUID): ReservationResponse

    fun findAllReservationByShopId(shopId: UUID): List<ReservationResponse>
}