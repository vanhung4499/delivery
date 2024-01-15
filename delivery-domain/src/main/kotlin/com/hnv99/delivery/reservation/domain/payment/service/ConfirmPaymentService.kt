package com.hnv99.delivery.reservation.domain.payment.service

import com.hnv99.delivery.reservation.domain.payment.Payment
import com.hnv99.delivery.reservation.domain.payment.PaymentGatewayService

class ConfirmPaymentService(
    private val paymentGatewayService: PaymentGatewayService
) {

    fun confirm(payment: Payment) {
        payment.approve()
        paymentGatewayService.confirm(payment)
    }
}