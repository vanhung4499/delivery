package com.hnv99.delivery

import com.hnv99.delivery.payload.Momo
import com.hnv99.delivery.payload.MomoCancelPayload
import com.hnv99.delivery.reservation.domain.payment.Payment
import com.hnv99.delivery.reservation.domain.payment.PaymentGatewayService
import org.springframework.stereotype.Component

private const val MOMO_API_BASE_URL = "https://test-payment.momo.vn"

@Component
class MomoGatewayService(
    private val momoWebClient: MomoWebClient
) : PaymentGatewayService {
    override fun confirm(payment: Payment) {
        val momo = toMomo(payment)

        momoWebClient.requestConfirm(momo, MOMO_API_BASE_URL)
    }

    override fun cancel(payment: Payment, reason: String) {
        val momo = toMomo(payment)

        momoWebClient.requestCancel(
            momo,
            MomoCancelPayload(reason),
            MOMO_API_BASE_URL
        )
    }

    private fun toMomo(payment: Payment) = Momo.of(
        paymentKey = payment.paymentKey,
        orderId = payment.reservation.id.toString(),
        amount = payment.amount.value
    )
}