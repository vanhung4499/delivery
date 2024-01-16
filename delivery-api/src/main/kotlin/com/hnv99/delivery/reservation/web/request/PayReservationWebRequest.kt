package com.hnv99.delivery.reservation.web.request

import com.hnv99.delivery.reservation.application.port.input.PayReservationRequest
import java.util.UUID
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class PayReservationWebRequest(
    @field:NotBlank(message = "Payment key must not be blank.")
    val paymentKey: String? = null,

    @field:NotNull(message = "Amount must not be null.")
    val amount: Long? = null
) {
    fun toRequest(reservationId: UUID): PayReservationRequest {
        return PayReservationRequest(
            paymentKey!!,
            reservationId,
            amount!!
        )
    }
}
