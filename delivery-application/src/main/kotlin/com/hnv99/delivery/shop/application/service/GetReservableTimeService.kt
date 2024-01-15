package com.hnv99.delivery.shop.application.service

import com.hnv99.delivery.shop.application.port.input.GetReservableTimeUseCase
import com.hnv99.delivery.shop.application.port.output.LoadReservableTimePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional(readOnly = true)
class GetReservableTimeService(
    private val loadReservableTimePort: LoadReservableTimePort
) : GetReservableTimeUseCase {
    override fun loadAllReservableTimeByShopId(shopId: UUID) = loadReservableTimePort.loadAllReservableTimeByShopId(shopId)
}