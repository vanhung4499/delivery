package com.hnv99.delivery.reservation.application.port.input

import java.util.*

interface RejectReservationUseCase {
    fun reject(reservationId: UUID)

}