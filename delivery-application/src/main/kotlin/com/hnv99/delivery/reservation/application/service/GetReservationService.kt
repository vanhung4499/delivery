package com.hnv99.delivery.reservation.application.service

import com.hnv99.delivery.reservation.application.port.input.GetReservationUseCase
import com.hnv99.delivery.reservation.application.port.input.ReservationResponse
import com.hnv99.delivery.reservation.application.port.output.LoadReservationPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class GetReservationService(
    private val loadReservationPort: LoadReservationPort
) : GetReservationUseCase {

    override fun loadReservationById(reservationId: UUID) =
        loadReservationPort.queryReservationById(reservationId)

    override fun loadAllReservationByShopId(shopId: UUID): List<ReservationResponse> =
        loadReservationPort.queryAllReservationByShopId(shopId)
}
