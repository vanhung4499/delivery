package com.hnv99.delivery.shop.domain.shop

import com.hnv99.delivery.common.models.Address
import com.hnv99.delivery.common.models.Coordinates
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Embedded

private const val CITY_CODE_LENGTH = 10

@Embeddable
class ShopAddress private constructor(
    @Column(name = "city_code", nullable = false)
    val cityCode: String,
    @Embedded
    val coordinates: Coordinates,
    @Embedded
    val address: Address
) {
    companion object {
        fun of(
            cityCode: String,
            coordinates: Coordinates,
            address: Address,
        ): ShopAddress {
            checkCityCodeLength(cityCode)

            return ShopAddress(
                cityCode = cityCode,
                coordinates = coordinates,
                address = address,
            )
        }

        private fun checkCityCodeLength(cityCode: String) {
            if (cityCode.length != CITY_CODE_LENGTH) {
                throw IllegalArgumentException("City code is not valid, length is not $CITY_CODE_LENGTH")
            }
        }
    }
}