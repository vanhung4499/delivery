package com.hnv99.delivery.shop.application.port.input

data class RegisterShopRequest(
    val title: String,
    val brn: String,
    val address: String
)
