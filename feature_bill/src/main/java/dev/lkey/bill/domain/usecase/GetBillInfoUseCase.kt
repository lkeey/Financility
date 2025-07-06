package dev.lkey.bill.domain.usecase

import dev.lkey.bill.domain.repository.BillRepository
import dev.lkey.common.core.model.AccountBriefModel
import dev.lkey.core.network.retryRequest
import jakarta.inject.Inject

/**
 * Use Case получения счетов
 * */

class GetBillInfoUseCase @Inject constructor(
    val billRepository: BillRepository
) {
    suspend operator fun invoke(): Result<List<AccountBriefModel>> {

        return retryRequest {
            val accounts = billRepository.getBillInfo()

            accounts
        }

    }
}
