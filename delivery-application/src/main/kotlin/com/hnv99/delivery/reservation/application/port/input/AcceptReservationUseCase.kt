package com.hnv99.delivery.reservation.application.port.input

import java.util.*

interface AcceptReservationUseCase {
    fun accept(reservationId: UUID)
}