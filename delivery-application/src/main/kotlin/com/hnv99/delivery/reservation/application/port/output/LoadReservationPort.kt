package com.hnv99.delivery.reservation.application.port.output

import com.hnv99.delivery.reservation.application.port.input.ReservationResponse
import com.hnv99.delivery.reservation.domain.reservation.Reservation
import java.util.*

interface LoadReservationPort {
    fun loadReservationById(reservationId: UUID): Reservation

    fun queryReservationById(reservationId: UUID): ReservationResponse

    fun queryAllReservationByShopId(shopId: UUID): List<ReservationResponse>
}