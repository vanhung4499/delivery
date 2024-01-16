package com.hnv99.delivery.shop.web.request

import com.hnv99.delivery.shop.application.port.input.RegisterShopRequest
import javax.validation.constraints.NotBlank

data class RegisterShopWebRequest (
    @field:NotBlank(message = "Title is required.")
    val title: String? = null,

    @field:NotBlank(message = "BRN is required.")
    val brn: String? = null,

    @field:NotBlank(message = "Address is required.")
    val address: String? = null
) {
    fun toRequest() = RegisterShopRequest(title!!, brn!!, address!!)
}