package com.hnv99.delivery.reservation.application.port.output

import com.hnv99.delivery.reservation.domain.reservation.Reservation
import java.util.*

interface SaveReservationPort {
    fun save(reservation: Reservation): UUID
}