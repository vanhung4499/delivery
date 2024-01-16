package com.hnv99.delivery.reservation.web

import com.hnv99.delivery.common.utils.HttpResponseUtils
import com.hnv99.delivery.common.utils.UUIDUtils
import com.hnv99.delivery.reservation.application.port.input.PayReservationUseCase
import com.hnv99.delivery.reservation.web.request.PayReservationWebRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/reservations")
class PayReservationController(
    private val payReservationUseCase: PayReservationUseCase
) {
    @PostMapping("/{reservationId}/pay")
    fun payReservation(
        @PathVariable("reservationId") reservationId: String,
        @Valid @RequestBody payReservationWebRequest: PayReservationWebRequest,
    ) : ResponseEntity<Unit> {
        val request = payReservationWebRequest.toRequest(UUIDUtils.fromString(reservationId))
        val resourceId = payReservationUseCase.pay(request)
        val location = HttpResponseUtils.createResourceUri("payments", resourceId)

        return ResponseEntity.created(location).build()
    }
}