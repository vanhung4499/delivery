package com.hnv99.delivery.shop.persistence

import com.hnv99.delivery.shop.domain.shop.Shop
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ShopRepository : JpaRepository<Shop, UUID> {
    @EntityGraph(attributePaths = ["products"])
    fun findWithProductsById(shopId: UUID): Optional<Shop>

    @EntityGraph(attributePaths = ["reservableTimes"])
    fun findWithReservableTimesById(shopId: UUID): Optional<Shop>
}