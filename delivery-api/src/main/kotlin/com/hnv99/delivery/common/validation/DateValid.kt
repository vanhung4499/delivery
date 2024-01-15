package com.hnv99.delivery.common.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.FUNCTION
)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [DateValidator::class])
annotation class DateValid(
    val message: String = "Invalid date format. Please enter in yyyy-mm-ddThh:mm:ss format.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
