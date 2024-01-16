package com.hnv99.delivery.reservation.application.service

import com.hnv99.delivery.reservation.application.port.input.ReserveProductRequest
import com.hnv99.delivery.reservation.application.port.input.ReserveProductUseCase
import com.hnv99.delivery.reservation.application.port.output.SaveReservationPort
import com.hnv99.delivery.reservation.domain.reservation.Reservation
import com.hnv99.delivery.shop.application.port.output.FineShopPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
@Transactional(readOnly = true)
class ReserveProductService(
    private val loadShopPort: FineShopPort,
    private val saveReservationPort: SaveReservationPort
) : ReserveProductUseCase {

    @Transactional
    override fun reserve(reserveProductRequest: ReserveProductRequest): UUID {
        val shop = loadShopPort.findById(reserveProductRequest.shopId)
        val reservationLineItem = reserveProductRequest.products.map { it.mapToDomainEntity() }
        val reservation = Reservation.of(
            lineItems = reservationLineItem,
            shop = shop,
            reserveAt = reserveProductRequest.reservedAt
        )

        reservation.reserve()

        return saveReservationPort.save(reservation)
    }
}
