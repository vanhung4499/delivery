package com.hnv99.delivery.shop.application.service

import com.hnv99.delivery.shop.application.port.input.GetReservableTimeUseCase
import com.hnv99.delivery.shop.application.port.output.FindReservableTimePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional(readOnly = true)
class GetReservableTimeService(
    private val loadReservableTimePort: FindReservableTimePort
) : GetReservableTimeUseCase {
    override fun findAllReservableTimeByShopId(shopId: UUID) = loadReservableTimePort.findAllReservableTimeByShopId(shopId)
}