package com.hnv99.delivery.reservation.application.port.input

import java.time.LocalDateTime
import java.util.*

data class ReservationResponse(
    val reservationId: UUID,
    val shopName: String,
    val description: String,
    val reserveAt: LocalDateTime,
    val status: String,
    val reservedProduct: List<ReservedProduct>
)
