package com.hnv99.delivery.common.constants

internal object ReservationConstants {
    object Validation {
        enum class ErrorMessage(val message: String) {
            INVALID_RESERVE_TIME("The store subject to reservation does not accept reservations at this time."),
            NOT_PAID_RESERVATION_CANNOT_ACCEPT("You cannot approve a reservation in unpaid status."),
            NOT_PAID_RESERVATION_CANNOT_REJECT("You cannot reject a reservation in unpaid status."),
            ALREADY_ACCEPTED_RESERVATION("It is already an approved reservation."),
            ALREADY_REJECTED_RESERVATION("It is already a rejected reservation."),
            ALREADY_REJECTED_RESERVATION_CANNOT_ACCEPT("You cannot approve a reservation that has already been rejected."),
            ALREADY_RESERVED_RESERVATION_CANNOT_REJECT("You cannot reject a reservation that has already been confirmed."),
            INVALID_RESERVATION_STATUS_FOR_PAYMENT("This feature can only be used in unpaid status."),
            AT_LEAST_ONE_ITEM_REQUIRED("Reservation items must be at least one or more."),
            AT_LEAST_ONE_ITEM_QUANTITY_REQUIRED("The quantity of individual items must be at least one or more."),
            FUTURE_TIME_REQUIRED("The reservation time must be at least a future point."),
            ALREADY_PROCESSED_RESERVATION("It is a reservation that is already being processed."),
            INVALID_RESERVATION_STATUS_FOR_OPERATION("It can only be performed for reservations that have been properly created.")
        }
    }
}
