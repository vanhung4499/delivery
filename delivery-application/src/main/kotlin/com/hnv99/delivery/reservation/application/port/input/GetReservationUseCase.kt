package com.hnv99.delivery.reservation.application.port.input

import java.util.*

interface GetReservationUseCase {
    fun loadReservationById(reservationId: UUID): ReservationResponse

    fun loadAllReservationByShopId(shopId: UUID): List<ReservationResponse>
}