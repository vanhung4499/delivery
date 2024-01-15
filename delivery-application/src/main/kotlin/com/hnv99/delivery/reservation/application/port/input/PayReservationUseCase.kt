package com.hnv99.delivery.reservation.application.port.input

import java.util.*

interface PayReservationUseCase {
    fun pay(payReservationRequest: PayReservationRequest): UUID
}