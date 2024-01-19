package com.hnv99.delivery.payload

private const val REASON_LIMIT_LENGTH = 200

data class MomoCancelPayload(
    val reason: String
) {
    init {
        if (reason.length > REASON_LIMIT_LENGTH) {
            throw IllegalArgumentException("reason must be less than $REASON_LIMIT_LENGTH characters")
        }
    }
}