package com.hnv99.delivery.reservation.domain.reservation

enum class ReservationStatus {
    NONE,
    NOT_PAID,
    PAID,
    RESERVED,
    REJECTED;

    fun isNone(): Boolean {
        return this == NONE
    }

    fun isPaid(): Boolean {
        return this == PAID
    }

    fun isReserved(): Boolean {
        return this == RESERVED
    }

    fun isRejected(): Boolean {
        return this == REJECTED
    }

    fun isNotPaid(): Boolean {
        return this == NOT_PAID
    }
}