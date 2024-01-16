package com.hnv99.delivery.firebase.mesage

import java.util.*

data class ReservationCreatedMessage(
    val title: String,
    val description: String,
    val reservationId: UUID,
    val token: String
)
