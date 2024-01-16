package com.hnv99.delivery.shop.web

import com.hnv99.delivery.common.utils.HttpResponseUtils
import com.hnv99.delivery.common.utils.UUIDUtils
import com.hnv99.delivery.reservation.application.port.input.GetReservationUseCase
import com.hnv99.delivery.shop.application.port.input.GetReservableTimeUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/shops")
class GetReservableTimeController(
    private val getReservableTimeUseCase: GetReservableTimeUseCase
) {

    @GetMapping("/{shopId}/reservable-time")
    fun getAllReservableTimeOfShop(@PathVariable("shopId") shopId: String) =
        HttpResponseUtils.mapToResponseEntity(
            list = getReservableTimeUseCase.findAllReservableTimeByShopId(UUIDUtils.fromString(shopId))
        )
}
