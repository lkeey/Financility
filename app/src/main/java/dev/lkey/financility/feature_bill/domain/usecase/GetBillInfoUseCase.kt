package dev.lkey.financility.feature_bill.domain.usecase

import dev.lkey.financility.feature_bill.data.repository.GetBillInfoRepositoryImpl
import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel

class GetBillInfoUseCase {
    private val repository = GetBillInfoRepositoryImpl()

    suspend fun invoke() : List<AccountBriefModel> {
        return repository.getBillInfo()
    }
}