package com.hnv99.delivery.common.models

import javax.persistence.Access
import javax.persistence.AccessType
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
@Access(AccessType.FIELD)
data class Money private constructor (
    @Column(name = "value", nullable = false)
    val value: Long,
){
    operator fun times(multiplier: Int): Money {
        return Money(value * multiplier)
    }

    operator fun plus(other: Money): Money {
        return Money(value + other.value)
    }

    operator fun minus(other: Money): Money {
        return Money(value - other.value)
    }

    fun lessThanEqual(other: Money): Boolean {
        return value <= other.value
    }

    companion object {
        fun from(value: Int): Money {
            checkValue(value)
            return Money(value.toLong())
        }
        private fun checkValue(value: Int) {
            if (value < 0) {
                throw IllegalArgumentException("Value must be greater than zero")
            }
        }
    }
}