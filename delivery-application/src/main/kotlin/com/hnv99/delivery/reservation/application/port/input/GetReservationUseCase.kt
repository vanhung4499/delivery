package com.hnv99.delivery.reservation.application.port.input

import java.util.*

interface GetReservationUseCase {
    fun findReservationById(reservationId: UUID): ReservationResponse

    fun findAllReservationByShopId(shopId: UUID): List<ReservationResponse>
}