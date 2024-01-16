package com.hnv99.delivery.shop.web

import com.hnv99.delivery.common.utils.HttpResponseUtils
import com.hnv99.delivery.common.utils.UUIDUtils
import com.hnv99.delivery.shop.application.port.input.GetProductUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/shops")
class GetProductController(
    private val getProductUseCase: GetProductUseCase
) {

    @GetMapping("/{shopId}/products")
    fun getAllProductOfShop(@PathVariable("shopId") shopId: String) =
        HttpResponseUtils.mapToResponseEntity(
            list = getProductUseCase.findAllProductByShopId(UUIDUtils.fromString(shopId))
        )
}
