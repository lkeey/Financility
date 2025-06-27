package dev.lkey.financility.feature_bill.domain.usecase

import dev.lkey.financility.core.network.retryRequest
import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel
import dev.lkey.financility.feature_bill.domain.repository.GetBillInfoRepository

/**
 * Use Case получения счетов
 * */

class GetBillInfoUseCase (
    val apiRepository: GetBillInfoRepository
) {
    suspend operator fun invoke(): Result<List<AccountBriefModel>> {

        return retryRequest {
            val accounts = apiRepository.getBillInfo()

            accounts
        }

    }
}