package com.hnv99.delivery.reservation.web.request

import com.hnv99.delivery.common.utils.UUIDUtils
import com.hnv99.delivery.common.validation.DateValid
import com.hnv99.delivery.reservation.application.port.input.ReserveProductRequest
import java.time.LocalDateTime
import javax.validation.Valid
import javax.validation.constraints.NotNull

data class ReserveProductWebRequest(
    @field:NotNull(message = "Shop ID must not be null.")
    val shopId: String? = null,

    @field:Valid
    @field:NotNull(message = "Products must not be null.")
    val products: List<ReservedWebProduct>? = null,

    @field:DateValid
    @field:NotNull(message = "Reserved time must not be null.")
    val reservedAt: String? = null,
)
{
    fun toReserveProductRequest() : ReserveProductRequest {
        return ReserveProductRequest(
            UUIDUtils.fromString(shopId!!),
            products!!.map { it.toReservedProduct() },
            LocalDateTime.parse(reservedAt!!)
        )
    }
}