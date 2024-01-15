package com.hnv99.delivery.reservation.application.port.input

import java.time.LocalDateTime
import java.util.*

data class ReserveProductRequest(
    val shopId: UUID,
    val products: List<ReservedProduct>,
    val reservedAt: LocalDateTime
)
