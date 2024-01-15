package com.hnv99.delivery.shop.domain.shop

import com.hnv99.delivery.common.models.UUIDBaseEntity
import com.hnv99.delivery.shop.domain.product.Product
import java.time.LocalDateTime
import java.time.LocalTime
import javax.persistence.*

@Entity
@Table(name = "shop")
class Shop(
    title: ShopTitle,
    status: ShopStatus,
    businessNumber: ShopBusinessNumber,
    address: ShopAddress,
    reservableTimes: MutableList<LocalTime>,
    products: MutableList<Product>
) : UUIDBaseEntity() {
    @Column(nullable = false)
    var title: ShopTitle = title
        protected set

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: ShopStatus = status
        protected set

    @ElementCollection
    @CollectionTable(
        name = "reservable_time",
        joinColumns = [JoinColumn(name = "shop_id")]
    )
    @Column(name = "reservable_time", nullable = false)
    var reservableTimes: MutableList<LocalTime> = reservableTimes
        protected set

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "shop_id")
    var products: MutableList<Product> = products
        protected set

    val businessNumber: ShopBusinessNumber = businessNumber

    val address: ShopAddress = address

    fun checkReservableShop() {
        if (status.isInvalidStatus()) {
            throw IllegalStateException("Shop is not reservable")
        }
    }

    fun checkItem(product: Product) {
        val match = products.filter { it == product }
        if (match.isEmpty()) {
            throw IllegalArgumentException("Product is not in shop")
        }
    }

    fun isReservableAt(reserveAt: LocalDateTime): Boolean {
        val match = reservableTimes.filter { it == reserveAt.toLocalTime() }
        return match.isNotEmpty()
    }

    fun changeStatusValid() {
        status = ShopStatus.VALID
    }

    fun changeStatusInvalid() {
        status = ShopStatus.INVALID
    }
}