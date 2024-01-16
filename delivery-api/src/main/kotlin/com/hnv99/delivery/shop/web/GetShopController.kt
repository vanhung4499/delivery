package com.hnv99.delivery.shop.web

import com.hnv99.delivery.common.utils.HttpResponseUtils
import com.hnv99.delivery.shop.application.port.input.GetShopUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/shops")
class GetShopController(
    private val getShopUseCase: GetShopUseCase
) {

    @GetMapping
    fun getAllShop() =
        HttpResponseUtils.mapToResponseEntity(list = getShopUseCase.findAllShop())
}
