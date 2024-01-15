package com.hnv99.delivery.shop.application.port.input

import java.util.UUID

interface GetProductUseCase {
    fun loadAllProductByShopId(shopId: UUID): List<ProductResponse>
}