package com.hnv99.delivery.reservation.application.port.output

import java.util.*

interface SendNewReservationMessagePort {
    fun sendNewReservationMessage(reservationId: UUID, description: String)
}