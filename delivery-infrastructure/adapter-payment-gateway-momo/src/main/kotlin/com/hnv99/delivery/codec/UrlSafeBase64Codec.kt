package com.hnv99.delivery.codec

import com.hnv99.delivery.MomoCodec
import org.springframework.stereotype.Component
import java.util.Base64

@Component
class UrlSafeBase64Codec : MomoCodec {

        override fun encode(data: String): String {
            val urlSafeEncoder = Base64.getUrlEncoder()
            val urlSafeEncodedBytes = urlSafeEncoder.encode(data.toByteArray())

            return String(urlSafeEncodedBytes)
        }

        override fun decode(data: String): String {
            val urlSafeDecoder = Base64.getUrlDecoder()
            val urlSafeDecodedBytes = urlSafeDecoder.decode(data)

            return String(urlSafeDecodedBytes)
        }
}