package com.hnv99.delivery

interface MomoCodec {
    fun encode(data: String): String

    fun decode(data: String): String
}