package com.hnv99.delivery.shop.application.port.input

import java.time.LocalTime
import java.util.UUID

interface GetReservableTimeUseCase {
    fun findAllReservableTimeByShopId(shopId: UUID): List<LocalTime>
}