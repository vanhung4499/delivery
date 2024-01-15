package com.hnv99.delivery.shop.application.port.input

import java.util.*

interface RegisterShopUseCase {
    fun register(registerShopRequest: RegisterShopRequest): UUID
}