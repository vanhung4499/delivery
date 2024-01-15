package com.hnv99.delivery.common.models

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Coordinates (
    @Column(name = "latitude", nullable = false)
    val latitude: Double,
    @Column(name = "longitude", nullable = false)
    val longitude: Double,
) {
    companion object {
        fun of(
            latitude: Double,
            longitude: Double,
        ): Coordinates {
            return Coordinates(
                latitude = latitude,
                longitude = longitude,
            )
        }

        private fun checkIsCoordinateRange(latitude: Double, longitude: Double) {
            if (latitude < -90 || latitude > 90) {
                throw IllegalArgumentException("Latitude must be in range [-90, 90]")
            }
            if (longitude < -180 || longitude > 180) {
                throw IllegalArgumentException("Longitude must be in range [-180, 180]")
            }
        }
    }
}