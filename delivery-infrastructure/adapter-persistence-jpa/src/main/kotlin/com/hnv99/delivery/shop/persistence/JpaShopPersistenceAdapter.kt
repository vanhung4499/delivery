package com.hnv99.delivery.shop.persistence

import com.hnv99.delivery.common.exception.EntityNotFoundException
import com.hnv99.delivery.reservation.persistence.ReservationRepository
import com.hnv99.delivery.shop.application.port.output.*
import com.hnv99.delivery.shop.domain.shop.Shop
import org.springframework.stereotype.Repository
import java.util.*

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

    override fun loadShopById(shopId: UUID) = getShopOrThrow(shopRepository::findOneWithProductsById, shopId)

    override fun loadAllProductByShopId(shopId: UUID) =
        getShopOrThrow(shopRepository::findOneWithProductsById, shopId).products

    override fun loadAllReservableTimeByShopId(shopId: UUID) =
        getShopOrThrow(shopRepository::findOneWithReservableTimesById, shopId).reservableTimes

    override fun hasReservations(shopId: UUID): Boolean {
        return reservationRepository.existsReservationByShopId(shopId)
    }

    private fun getShopOrThrow(queryMethod: Function<UUID, Optional<Shop>>, shopId: UUID): Shop {
        return (queryMethod.apply(shopId).orElseThrow { throw EntityNotFoundException(NOT_FOUND_SHOP_MESSAGE) })
    }
}
