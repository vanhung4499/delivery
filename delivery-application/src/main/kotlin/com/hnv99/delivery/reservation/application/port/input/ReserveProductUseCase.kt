package com.hnv99.delivery.reservation.application.port.input

import java.util.*

interface ReserveProductUseCase {
    fun reserve(reserveProductRequest: ReserveProductRequest): UUID
}