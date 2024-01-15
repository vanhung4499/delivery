package com.hnv99.delivery.common.models

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Address private constructor(
    @Column(name = "address", nullable = false)
    val address: String,
    @Column(name = "ward", nullable = false)
    val ward: String,
    @Column(name = "district", nullable = false)
    val district: String,
    @Column(name = "city", nullable = false)
    val city: String,
    @Column(name = "country", nullable = false)
    val country: String,
) {
    companion object {
        fun of(
            address: String,
            ward: String,
            district: String,
            city: String,
            country: String,
        ): Address {
            return Address(
                address = address,
                ward = ward,
                district = district,
                city = city,
                country = country,
            )
        }
    }
}