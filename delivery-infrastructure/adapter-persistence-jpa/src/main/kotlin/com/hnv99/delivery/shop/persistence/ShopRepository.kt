package com.hnv99.delivery.shop.persistence

import com.hnv99.delivery.shop.domain.shop.Shop
import net.bytebuddy.dynamic.DynamicType.Builder.RecordComponentDefinition.Optional
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ShopRepository : JpaRepository<Shop, Long> {
    @EntityGraph(attributePaths = ["products"])
    fun findOneWithProductsById(shopId: UUID): Optional<Shop>

    @EntityGraph(attributePaths = ["reservableTimes"])
    fun findOneWithReservableTimesById(shopId: UUID): Optional<Shop>
}