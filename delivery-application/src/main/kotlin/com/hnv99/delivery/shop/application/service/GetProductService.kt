package com.hnv99.delivery.shop.application.service

import com.hnv99.delivery.shop.application.port.input.GetProductUseCase
import com.hnv99.delivery.shop.application.port.input.ProductResponse
import com.hnv99.delivery.shop.application.port.output.LoadProductPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional(readOnly = true)
class GetProductService(
    private val loadProductPort: LoadProductPort
) : GetProductUseCase {
    override fun loadAllProductByShopId(shopId: UUID) = loadProductPort.loadAllProductByShopId(shopId)
        .map { ProductResponse(it.id.id, it.name, it.price.value) }
}