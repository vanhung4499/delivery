package com.hnv99.delivery.reservation.domain.payment.service

import com.hnv99.delivery.reservation.domain.payment.Payment
import com.hnv99.delivery.reservation.domain.payment.PaymentGatewayService

class CancelPaymentService (
    private val paymentGateWayService: PaymentGatewayService,
) {
    fun cancel(payment: Payment) {
        paymentGateWayService.cancel(payment, "Reservation refused by shop owner")
    }
}