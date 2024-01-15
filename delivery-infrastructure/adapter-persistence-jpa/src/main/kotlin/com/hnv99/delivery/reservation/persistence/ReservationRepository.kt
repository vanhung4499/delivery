package com.hnv99.delivery.reservation.persistence

import com.hnv99.delivery.reservation.domain.reservation.Reservation
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ReservationRepository : JpaRepository<Reservation, UUID> {
    @EntityGraph(attributePaths = ["shop"])
    fun findWithShopById(reservationId: UUID): Optional<Reservation>

    fun findAllByShopId(shopId: UUID): List<Reservation>

    fun existsReservationByShopId(shopId: UUID): Boolean
}