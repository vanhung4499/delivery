package com.hnv99.delivery.reservation.domain.reservation

import com.hnv99.delivery.common.constants.ReservationConstants.Validation.ErrorMessage.AT_LEAST_ONE_ITEM_QUANTITY_REQUIRED
import com.hnv99.delivery.common.models.Money
import com.hnv99.delivery.shop.domain.product.Product
import com.hnv99.delivery.shop.domain.product.ProductId
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Embedded

@Embeddable
class ReservationLineItem private constructor(
    itemId: ProductId,
    name: String,
    price: Money,
    quantity: Int,
) {

    @Embedded
    var itemId: ProductId = itemId
        protected set

    @Column(name = "name", nullable = false)
    var name: String = name
        protected set

    @Embedded
    var price: Money = price
        protected set

    @Column(name = "quantity", nullable = false)
    var quantity: Int = quantity
        protected set

    fun mapToProduct(): Product {
        return Product(
            id = itemId,
            name = name,
            price = price,
        )
    }

    fun calculateEachItemTotalPrice() = price * quantity

    companion object {
        fun of(
            itemId: ProductId,
            name: String,
            price: Money,
            quantity: Int,
        ): ReservationLineItem {
            checkQuantity(quantity)

            return ReservationLineItem(
                itemId = itemId,
                name = name,
                price = price,
                quantity = quantity,
            )
        }

        private fun checkQuantity(quantity: Int) {
            if (quantity < 1) {
                throw IllegalArgumentException(AT_LEAST_ONE_ITEM_QUANTITY_REQUIRED.message)
            }
        }
    }
}