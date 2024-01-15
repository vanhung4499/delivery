package com.hnv99.delivery.reservation.application.port.input

import com.hnv99.delivery.common.models.Money
import com.hnv99.delivery.reservation.domain.reservation.ReservationLineItem
import com.hnv99.delivery.shop.domain.product.ProductId

data class ReservedProduct(
    val productId: Long,
    val name: String,
    val price: Long,
    val quantity: Int
) {
    fun mapToDomainEntity() = ReservationLineItem.of(
        ProductId(productId),
        name,
        Money.from(price),
        quantity
    )
}
