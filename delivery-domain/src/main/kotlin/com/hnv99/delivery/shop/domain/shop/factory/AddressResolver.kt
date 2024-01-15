package com.hnv99.delivery.shop.domain.shop.factory

import com.hnv99.delivery.shop.domain.shop.ShopAddress

interface AddressResolver {
    fun resolve(address: String): ShopAddress
}
