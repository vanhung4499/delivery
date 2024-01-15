package com.hnv99.delivery.shop.persistence

import com.hnv99.delivery.common.exception.EntityNotFoundException
import com.hnv99.delivery.reservation.persistence.ReservationRepository
import com.hnv99.delivery.shop.application.port.output.*
import com.hnv99.delivery.shop.domain.product.Product
import com.hnv99.delivery.shop.domain.shop.Shop
import org.springframework.stereotype.Repository
import java.time.LocalTime
import java.util.UUID

private const val NOT_FOUND_SHOP_MESSAGE = "Shop information not found."
@Repository
class JpaShopPersistenceAdapter(
    private val shopRepository: ShopRepository,
    private val reservationRepository: ReservationRepository,
) : SaveShopPort, LoadShopPort, LoadProductPort, LoadReservableTimePort, CheckExistenceShopPort {

    override fun save(shop: Shop): UUID {
        shopRepository.save(shop)

        return shop.id
    }

    override fun loadAllShop(): List<Shop> = shopRepository.findAll()

    override fun loadShopById(shopId: UUID): Shop {
        return shopRepository.findById(shopId)
            .orElseThrow { throw EntityNotFoundException(NOT_FOUND_SHOP_MESSAGE) }
    }

    override fun loadAllProductByShopId(shopId: UUID): List<Product> {
        return shopRepository.findWithProductsById(shopId)
            .orElseThrow { throw EntityNotFoundException(NOT_FOUND_SHOP_MESSAGE) }
    .products
    }

    override fun loadAllReservableTimeByShopId(shopId: UUID): List<LocalTime> {
        return shopRepository.findWithReservableTimesById(shopId)
            .orElseThrow { throw EntityNotFoundException(NOT_FOUND_SHOP_MESSAGE) }
            .reservableTimes
    }

    override fun hasReservations(shopId: UUID): Boolean {
        return reservationRepository.existsReservationByShopId(shopId)
    }

}
