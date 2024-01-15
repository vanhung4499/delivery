package com.hnv99.delivery.reservation.application.port.output

import com.hnv99.delivery.reservation.domain.payment.Payment
import java.util.*

interface SavePaymentPort {
    fun save(payment: Payment): UUID

}