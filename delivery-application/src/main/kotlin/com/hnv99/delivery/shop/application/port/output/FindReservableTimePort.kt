package com.hnv99.delivery.shop.application.port.output

import java.time.LocalTime
import java.util.UUID

interface FindReservableTimePort {
    fun findAllReservableTimeByShopId(shopId: UUID): List<LocalTime>
}