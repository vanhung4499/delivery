package com.hnv99.delivery.shop.domain.shop

import javax.persistence.Column
import javax.persistence.Embeddable

private const val MAX_TITLE_LENGTH = 50
private const val TITLE_FORMAT = "^[a-zA-Z0-9\\s]*"

@Embeddable
class ShopTitle private constructor(
    @Column(name = "title", nullable = false)
    val title: String,
) {
    companion object {
        fun from(title: String): ShopTitle {
            checkTitleBlank(title)
            checkTitleLength(title)
            checkTitleFormat(title)

            return ShopTitle(title)
        }

        private fun checkTitleFormat(title: String) {
            if (!title.matches(getTitleFormatRegex())) {
                throw IllegalArgumentException("Title format is not valid")
            }
        }

        private fun checkTitleBlank(title: String) {
            if (title.isBlank()) {
                throw IllegalArgumentException("Title is blank")
            }
        }

        private fun checkTitleLength(title: String) {
            if (title.length > MAX_TITLE_LENGTH) {
                throw IllegalArgumentException("Title length is too long")
            }
        }

        private fun getTitleFormatRegex() = Regex(TITLE_FORMAT)
    }
}