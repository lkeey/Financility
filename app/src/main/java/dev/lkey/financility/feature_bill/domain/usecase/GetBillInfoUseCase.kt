package dev.lkey.financility.feature_bill.domain.usecase

import dev.lkey.financility.core.network.retryRequest
import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel
import dev.lkey.financility.feature_bill.domain.repository.BillRepository

/**
 * Use Case получения счетов
 * */

class GetBillInfoUseCase (
    val apiRepository: BillRepository
) {
    suspend operator fun invoke(): Result<List<AccountBriefModel>> {

        return retryRequest {
            val accounts = apiRepository.getBillInfo()

            accounts
        }

    }
}
