package com.hnv99.delivery.shop.application.port.output

import java.util.UUID

interface CheckExistShopPort {
    fun hasReservations(shopId: UUID): Boolean
}