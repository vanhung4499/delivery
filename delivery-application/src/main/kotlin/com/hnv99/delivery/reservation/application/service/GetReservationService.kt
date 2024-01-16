package com.hnv99.delivery.reservation.application.service

import com.hnv99.delivery.reservation.application.port.input.GetReservationUseCase
import com.hnv99.delivery.reservation.application.port.input.ReservationResponse
import com.hnv99.delivery.reservation.application.port.output.FindReservationPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class GetReservationService(
    private val findReservationPort: FindReservationPort
) : GetReservationUseCase {

    override fun findReservationById(reservationId: UUID) =
        findReservationPort.queryReservationById(reservationId)

    override fun findAllReservationByShopId(shopId: UUID): List<ReservationResponse> =
        findReservationPort.findAllReservationByShopId(shopId)
}
