package com.hnv99.delivery.reservation.application.port.input

import java.util.UUID

interface AcceptReservationUseCase {
    fun accept(reservationId: UUID)
}