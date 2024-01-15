package com.hnv99.delivery.common.exception

import org.springframework.validation.BindingResult
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY
import org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR


data class ErrorResponse(
    val status: Int,
    val message: String,
    val errors: List<FieldError>
) {
    companion object {
        fun badRequest(bindingResult: BindingResult) =
            ErrorResponse(BAD_REQUEST.value(), "Invalid Parameters", FieldError.of(bindingResult))

        fun badRequest(message: String) = ErrorResponse(BAD_REQUEST.value(), message, emptyList())

        fun unprocessableEntity(message: String) = ErrorResponse(UNPROCESSABLE_ENTITY.value(), message, emptyList())

        fun notFound(message: String) =
            ErrorResponse(NOT_FOUND.value(), message, emptyList())

        fun methodNotAllowed() =
            ErrorResponse(METHOD_NOT_ALLOWED.value(), "Method Not Allowed", emptyList())

        fun internalServerError() =
            ErrorResponse(INTERNAL_SERVER_ERROR.value(), "Internal Server Error", emptyList())
    }
}