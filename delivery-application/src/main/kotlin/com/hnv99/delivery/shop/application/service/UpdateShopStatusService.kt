package com.hnv99.delivery.shop.application.service

import com.hnv99.delivery.shop.application.port.input.UpdateShopStatusUseCase
import com.hnv99.delivery.shop.application.port.output.CheckExistenceShopPort
import com.hnv99.delivery.shop.application.port.output.FineShopPort
import com.hnv99.delivery.shop.domain.shop.Shop
import java.util.UUID

class UpdateShopStatusService(
    private val checkExistenceShopPort: CheckExistenceShopPort,
    private val loadShopPort: FineShopPort
) : UpdateShopStatusUseCase {
    override fun update(shopId: UUID) {
        val shop = loadShopPort.findById(shopId)

        if (shop.status.isValidStatus()) {
            checkReservationByShopId(shopId)
        }
        updateStatus(shop)
    }

    private fun checkReservationByShopId(shopId: UUID) {
        if (checkExistenceShopPort.hasReservations(shopId)) {
            throw IllegalStateException("The shop status cannot be changed to INVALID if a reservation exists")
        }
    }

    private fun updateStatus(shop: Shop) {
        if (shop.status.isValidStatus()) {
            shop.changeStatusInvalid()
        } else {
            shop.changeStatusValid()
        }
    }
}