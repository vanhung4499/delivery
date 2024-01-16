package com.hnv99.delivery.shop.application.port.input

interface GetShopUseCase {
    fun findAllShop(): List<ShopResponse>
}