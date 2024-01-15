package com.hnv99.delivery.reservation.domain.payment

enum class PaymentStatus {
    READY,
    APPROVED,
    CANCELED;

    fun isApproved(): Boolean {
        return this == APPROVED
    }
}