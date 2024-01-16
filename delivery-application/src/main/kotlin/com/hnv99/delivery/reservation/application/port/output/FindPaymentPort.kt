package com.hnv99.delivery.reservation.application.port.output

import com.hnv99.delivery.reservation.domain.payment.Payment
import java.util.*

interface FindPaymentPort {
    fun findPaymentByReservationId(reservationId: UUID): Payment
}