package com.hnv99.delivery.common.utils

import java.util.*

class UUIDUtils {
    companion object {
        fun fromString(uuidString: String): UUID {
            try {
                return UUID.fromString(uuidString)
            } catch (e: Exception) {
                throw IllegalArgumentException("Invalid UUID format.")
            }
        }
    }
}