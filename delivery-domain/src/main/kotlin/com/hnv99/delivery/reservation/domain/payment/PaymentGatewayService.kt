package com.hnv99.delivery.reservation.domain.payment

interface PaymentGatewayService {
    fun confirm(payment: Payment)
    fun cancel(payment: Payment, reason: String)
}