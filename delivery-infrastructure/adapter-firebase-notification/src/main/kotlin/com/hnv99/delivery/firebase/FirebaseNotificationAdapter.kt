package com.hnv99.delivery.firebase

import com.hnv99.delivery.firebase.mesage.ReservationCreatedMessage
import com.hnv99.delivery.firebase.mesage.ReservationStatusChangedMessage
import com.hnv99.delivery.reservation.application.port.output.SendAcceptedReservationMessagePort
import com.hnv99.delivery.reservation.application.port.output.SendNewReservationMessagePort
import com.hnv99.delivery.reservation.application.port.output.SendRejectedReservationMessagePort
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class FirebaseNotificationAdapter(
    @Value("\${admin.fcm.token}")
    private val adminToken: String,
    @Value("\${client.fcm.token}")
    private val clientToken: String,
    private val client: FirebaseNotificationClient
) : SendNewReservationMessagePort, SendAcceptedReservationMessagePort, SendRejectedReservationMessagePort {
    override fun sendNewReservationMessage(reservationId: UUID, description: String) {
        client.send(ReservationCreatedMessage("A new reservation has arrived!", description, reservationId, adminToken))
    }

    override fun sendAcceptedReservationMessage() {
        client.send(ReservationStatusChangedMessage("Your reservation has been approved!", clientToken))
    }

    override fun sendRejectedReservationMessage() {
        client.send(ReservationStatusChangedMessage("Sorry. Your reservation was rejected due to circumstances at the store you made the reservation at.", clientToken))
    }
}