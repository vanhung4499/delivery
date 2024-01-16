package com.hnv99.delivery.shop.web

import com.hnv99.delivery.common.utils.UUIDUtils
import com.hnv99.delivery.shop.application.port.input.UpdateShopStatusUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/shops")
class UpdateShopStatusController(
    private val updateShopStatusUseCase: UpdateShopStatusUseCase
) {

    @PostMapping("/{shopId}/status")
    fun updateShopStatus(@PathVariable("shopId") shopId: String): ResponseEntity<Unit> {
        updateShopStatusUseCase.update(UUIDUtils.fromString(shopId))

        return ResponseEntity.noContent().build()
    }
}
