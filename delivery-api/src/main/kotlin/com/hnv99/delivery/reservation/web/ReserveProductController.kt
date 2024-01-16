package com.hnv99.delivery.reservation.web

import com.hnv99.delivery.common.utils.HttpResponseUtils
import com.hnv99.delivery.reservation.application.port.input.ReserveProductUseCase
import com.hnv99.delivery.reservation.web.request.ReserveProductWebRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@RestController
@RequestMapping("/reservations")
class ReserveProductController(
    private val reserveProductUseCase: ReserveProductUseCase
) {

    @PostMapping
    fun reserveProduct(@Valid @RequestBody reserveProductWebRequest: ReserveProductWebRequest): ResponseEntity<Unit> {
        val resourceId = reserveProductUseCase.reserve(reserveProductWebRequest.toReserveProductRequest())
        val location = HttpResponseUtils.createResourceUri(resourceId)

        return ResponseEntity.created(location).build()
    }
}
