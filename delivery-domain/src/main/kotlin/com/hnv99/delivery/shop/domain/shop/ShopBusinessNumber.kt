package com.hnv99.delivery.shop.domain.shop

import javax.persistence.Column
import javax.persistence.Embeddable

private const val BRN_FORMAT = "^[0-9]{10}$"

@Embeddable
class ShopBusinessNumber private constructor(
    @Column(name = "brn", nullable = false)
    val value: String,
) {
    companion object {
        fun from(brn: String): ShopBusinessNumber {
            checkBrnFormat(brn)

            return ShopBusinessNumber(brn)
        }

        private fun checkBrnFormat(brn: String) {
            if (!brn.matches(getBrnFormatRegex())) {
                throw IllegalArgumentException("BRN format is not valid")
            }
        }

        private fun getBrnFormatRegex() = Regex(BRN_FORMAT)
    }
}