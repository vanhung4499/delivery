package com.hnv99.delivery.reservation.web.request

import com.hnv99.delivery.reservation.application.port.input.ReservedProduct
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class ReservedWebProduct(
    @field:NotNull(message = "Product ID must not be null.")
    val productId: Long? = null,

    @field:NotBlank(message = "Product name must not be blank.")
    val name: String? = null,

    @field:NotNull(message = "Product price must not be null.")
    val price: Long? = null,

    @field:NotNull(message = "Product quantity must not be null.")
    val quantity: Int? = null
) {
    fun toReservedProduct() = ReservedProduct(
        productId!!,
        name!!,
        price!!,
        quantity!!
    )
}
