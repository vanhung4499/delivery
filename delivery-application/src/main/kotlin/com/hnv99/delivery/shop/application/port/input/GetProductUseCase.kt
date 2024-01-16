package com.hnv99.delivery.shop.application.port.input

import java.util.UUID

interface GetProductUseCase {
    fun findAllProductByShopId(shopId: UUID): List<ProductResponse>
}