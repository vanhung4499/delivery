package com.hnv99.delivery.reservation.application.service

import com.hnv99.delivery.reservation.application.port.output.FindReservationPort
import com.hnv99.delivery.reservation.application.port.output.SendAcceptedReservationMessagePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional(readOnly = true)
class AcceptReservationService(
    private val findReservationPort: FindReservationPort,
    private val sendAcceptedReservationMessagePort: SendAcceptedReservationMessagePort
) {
    @Transactional
    fun accept(reservationId: UUID) {
        val reservation = findReservationPort.findReservationById(reservationId)
        reservation.accept()
        sendAcceptedReservationMessagePort.sendAcceptedReservationMessage()
    }
}