package com.hnv99.delivery.reservation.application.port.input

import java.util.*

data class PayReservationRequest(
    val paymentKey: String,
    val reservationId: UUID,
    val amount: Long
)
