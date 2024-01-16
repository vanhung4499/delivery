package com.hnv99.delivery.reservation.web

import com.hnv99.delivery.common.utils.UUIDUtils
import com.hnv99.delivery.reservation.application.port.input.GetReservationUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/reservations")
class GetReservationController(
    private val getReservationUseCase: GetReservationUseCase
) {
    @GetMapping("/{reservationId}")
    fun getReservation(@PathVariable("reservationId") reservationId: String) =
        getReservationUseCase.findReservationById(UUIDUtils.fromString(reservationId))
}