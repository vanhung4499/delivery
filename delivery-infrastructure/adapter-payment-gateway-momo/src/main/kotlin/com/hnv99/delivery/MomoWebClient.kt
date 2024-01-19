package com.hnv99.delivery

import com.hnv99.delivery.common.exception.EntityNotFoundException
import com.hnv99.delivery.payload.Momo
import com.hnv99.delivery.payload.MomoCancelPayload
import com.hnv99.delivery.payload.MomoErrorResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.nio.charset.StandardCharsets

@Component
class MomoWebClient(
    private val codec: MomoCodec,
    private val webClient: WebClient,
    @Value("\${pg.momo.secretKey}")
    private val secretKey: String
) {
    fun requestConfirm(payment: Momo, baseUrl: String) {
        webClient.post()
            .uri("$baseUrl/pay/confirm")
            .headers { setHeader(it, payment) }
            .body(Mono.just(payment), Momo::class.java)
            .exchangeToMono { exceptionHandler(it) }
            .block()
    }

    fun requestCancel(payment: Momo, payload: MomoCancelPayload, baseUrl: String) {
        webClient.post()
            .uri("$baseUrl/pay/cancel")
            .headers { setHeader(it, payment) }
            .body(Mono.just(payload), MomoCancelPayload::class.java)
            .exchangeToMono { exceptionHandler(it) }
            .block()
    }

    private fun setHeader(
        headers: HttpHeaders,
        payment: Momo
    ) {
        headers.acceptCharset = listOf(StandardCharsets.UTF_8)
        headers.contentType = MediaType.APPLICATION_JSON
        headers.setBasicAuth(codec.encode("$secretKey:"))
        headers.set("Idempotency-Key", payment.orderId)
    }

    private fun exceptionHandler(response: ClientResponse): Mono<Throwable> {
        return when (response.statusCode()) {
            HttpStatus.OK -> Mono.empty()

            HttpStatus.BAD_REQUEST -> response.bodyToMono(MomoErrorResponse::class.java).flatMap {
                Mono.error(IllegalArgumentException(it.message))
            }

            HttpStatus.NOT_FOUND -> response.bodyToMono(MomoErrorResponse::class.java).flatMap {
                Mono.error(EntityNotFoundException(it.message))
            }

            else -> Mono.error(RuntimeException())
        }
    }
}