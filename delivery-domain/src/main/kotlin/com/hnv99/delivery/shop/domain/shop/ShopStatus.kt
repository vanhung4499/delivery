package com.hnv99.delivery.shop.domain.shop

enum class ShopStatus {

    INVALID,
    VALID;

    fun isInvalidStatus(): Boolean {
        return this == INVALID
    }

    fun isValidStatus(): Boolean {
        return this == VALID
    }
}
