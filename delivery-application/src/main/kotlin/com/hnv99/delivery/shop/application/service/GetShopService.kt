package com.hnv99.delivery.shop.application.service

import com.hnv99.delivery.shop.application.port.input.GetShopUseCase
import com.hnv99.delivery.shop.application.port.input.ShopResponse
import com.hnv99.delivery.shop.application.port.output.LoadShopPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GetShopService(
    private val loadShopPort: LoadShopPort
) : GetShopUseCase {
    override fun loadAllShop() = loadShopPort.loadAllShop().map { ShopResponse(it.id, it.title.title) }
}