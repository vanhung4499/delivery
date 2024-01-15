package com.hnv99.delivery.shop.domain.shop.factory

import com.hnv99.delivery.shop.domain.product.Product
import com.hnv99.delivery.shop.domain.shop.Shop
import com.hnv99.delivery.shop.domain.shop.ShopBusinessNumber
import com.hnv99.delivery.shop.domain.shop.ShopStatus
import com.hnv99.delivery.shop.domain.shop.ShopTitle
import org.springframework.stereotype.Component
import java.time.LocalTime

@Component
class ShopFactory(
    private val addressResolver: AddressResolver,
    private val shopBusinessNumberValidator: ShopBusinessNumberValidator,
) {
    fun create(
        title: String,
        brn: String,
        address: String,
    ): Shop {
        val shopBusinessNumber = ShopBusinessNumber.from(brn)

        val shopAddress = addressResolver.resolve(address)

        shopBusinessNumberValidator.validate(shopBusinessNumber)

        return Shop(
            ShopTitle.from(title),
            ShopStatus.VALID,
            shopBusinessNumber,
            shopAddress,
            emptyList<LocalTime>().toMutableList(),
            emptyList<Product>().toMutableList(),
        )
    }
}