package com.hnv99.delivery.shop.application.service

import com.hnv99.delivery.shop.application.port.input.RegisterShopRequest
import com.hnv99.delivery.shop.application.port.input.RegisterShopUseCase
import com.hnv99.delivery.shop.application.port.output.SaveShopPort
import com.hnv99.delivery.shop.domain.shop.factory.ShopFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional(readOnly = true)
class RegisterShopService(
    private val saveShopPort: SaveShopPort,
    private val shopFactory: ShopFactory,
) : RegisterShopUseCase {
    override fun register(registerShopRequest: RegisterShopRequest) : UUID {
        val shop = shopFactory.create(
            registerShopRequest.title,
            registerShopRequest.brn,
            registerShopRequest.address,
        )
        return saveShopPort.save(shop)
    }
}