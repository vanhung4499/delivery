package com.hnv99.delivery.reservation.domain.payment

import com.hnv99.delivery.common.models.Money
import com.hnv99.delivery.common.models.UUIDBaseEntity
import com.hnv99.delivery.reservation.domain.reservation.Reservation
import javax.persistence.*

@Entity
class Payment private constructor(
    paymentKey: String,
    reservation: Reservation,
    amount: Money,
) : UUIDBaseEntity() {
    @Column(nullable = false)
    val paymentKey: String = paymentKey

    @OneToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    val reservation: Reservation = reservation

    @Embedded
    val amount: Money = amount

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var paymentStatus: PaymentStatus = PaymentStatus.READY
        protected set

    fun approve() {
        checkAlreadyApproved()

        reservation.pay()
        paymentStatus = PaymentStatus.APPROVED
    }

    private fun checkAlreadyApproved() {
        if (paymentStatus.isApproved()) {
            throw IllegalStateException("Payment is already approved")
        }
    }

    fun cancel() {
        reservation.reject()

        paymentStatus = PaymentStatus.CANCELED
    }

    companion object {
        fun of(
            paymentKey: String,
            reservation: Reservation,
            amount: Money,
        ): Payment {
            checkAmount(reservation, amount)

            return Payment(
                paymentKey = paymentKey,
                reservation = reservation,
                amount = amount,
            )
        }

        private fun checkAmount(reservation: Reservation, amount: Money) {
            if (amount.lessThanEqual(Money.from(0))) {
                throw IllegalArgumentException("Amount must be greater than 0")
            }

            if (amount != reservation.calculateTotalPrice()) {
                throw IllegalArgumentException("Amount must be equal to total price")
            }
        }
    }
}