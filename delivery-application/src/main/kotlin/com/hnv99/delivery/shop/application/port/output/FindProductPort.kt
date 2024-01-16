package com.hnv99.delivery.shop.application.port.output

import com.hnv99.delivery.shop.domain.product.Product
import java.util.UUID

interface FindProductPort {
    fun findAllProductByShopId(shopId: UUID): List<Product>
}