package com.hnv99.delivery.shop.application.port.output

import com.hnv99.delivery.shop.domain.shop.Shop
import java.util.UUID

interface LoadShopPort {
    fun loadAllShop(): List<Shop>
    fun loadShopById(shopId: UUID): Shop
}