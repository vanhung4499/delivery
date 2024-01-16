package com.hnv99.delivery.shop.application.port.output

import com.hnv99.delivery.shop.domain.shop.Shop
import java.util.UUID

interface FineShopPort {
    fun findAllShop(): List<Shop>
    fun findById(shopId: UUID): Shop
}