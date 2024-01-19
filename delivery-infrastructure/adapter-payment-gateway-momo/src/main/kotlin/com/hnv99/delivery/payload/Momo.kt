package com.hnv99.delivery.payload

private const val MAX_PAYMENT_KEY_LENGTH = 200
private const val MIN_ORDER_ID_LENGTH = 6
private const val MAX_ORDER_ID_LENGTH = 64
private const val ORDER_ID_FORMAT = "^([0-9a-zA-Z\\-_]+)"

class Momo private constructor(
    val paymentKey: String,
    val orderId: String,
    val amount: Long,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Momo

        if (paymentKey != other.paymentKey) return false
        if (orderId != other.orderId) return false
        return amount == other.amount
    }

    override fun hashCode(): Int {
        var result = paymentKey.hashCode()
        result = 31 * result + orderId.hashCode()
        result = 31 * result + amount.hashCode()
        return result
    }

    companion object {
        fun of(paymentKey: String, orderId: String, amount: Long): Momo {
            checkPaymentKeyLength(paymentKey)
            checkAmount(amount)
            checkOrderIdFormat(orderId)
            checkOrderIdLengthInRange(orderId)

            return Momo(paymentKey, orderId, amount)
        }

        private fun checkOrderIdFormat(orderId: String) {
            if (!orderId.matches(getOrderIdFormatRegex())) {
                throw IllegalArgumentException("The order number (orderId) must consist of upper and lower case letters, numbers, and special characters - and _.")
            }
        }

        private fun getOrderIdFormatRegex() = Regex(ORDER_ID_FORMAT)


        private fun checkPaymentKeyLength(paymentKey: String) {
            if (paymentKey.length > MAX_PAYMENT_KEY_LENGTH) {
                throw IllegalArgumentException("The payment key value (paymentKey) is a maximum of $MAX_PAYMENT_KEY_LENGTH characters.")
            }
        }

        private fun checkAmount(amount: Long) {
            if (amount <= 0) {
                throw IllegalArgumentException("The payment amount (amount) must be greater than 0.")
            }
        }

        private fun checkOrderIdLengthInRange(orderId: String) {
            if (orderId.length < MIN_ORDER_ID_LENGTH || orderId.length > MAX_ORDER_ID_LENGTH) {
                throw IllegalArgumentException("The order number (orderId) must be between $MIN_ORDER_ID_LENGTH and $MAX_ORDER_ID_LENGTH characters.")
            }
        }
    }

}