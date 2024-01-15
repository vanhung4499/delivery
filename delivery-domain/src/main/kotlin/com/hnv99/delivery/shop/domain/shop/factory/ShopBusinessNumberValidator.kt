package com.hnv99.delivery.shop.domain.shop.factory

import com.hnv99.delivery.shop.domain.shop.ShopBusinessNumber

interface ShopBusinessNumberValidator {
    fun validate(brn: ShopBusinessNumber)
}