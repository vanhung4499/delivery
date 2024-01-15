package com.hnv99.delivery.reservation.persistence

import com.hnv99.delivery.reservation.domain.payment.Payment
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PaymentRepository : JpaRepository<Payment, UUID> {
    @EntityGraph(attributePaths = ["reservation"])
    fun findByReservationId(reservationId: UUID): Optional<Payment>
}