package com.hnv99.delivery.reservation.application.service

import com.hnv99.delivery.reservation.application.port.output.LoadReservationPort
import com.hnv99.delivery.reservation.application.port.output.SendAcceptedReservationMessagePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional(readOnly = true)
class AcceptReservationService(
    private val loadReservationPort: LoadReservationPort,
    private val sendAcceptedReservationMessagePort: SendAcceptedReservationMessagePort
) {
    @Transactional
    fun accept(reservationId: UUID) {
        val reservation = loadReservationPort.loadReservationById(reservationId)
        reservation.accept()
        sendAcceptedReservationMessagePort.sendAcceptedReservationMessage()
    }
}