package dev.lkey.financility.feature_bill.domain.usecase

import dev.lkey.core.network.retryRequest
import dev.lkey.financility.feature_bill.data.model.UpdateAccountDto
import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel
import dev.lkey.financility.feature_bill.domain.repository.BillRepository

/**
 * Use Case обновления счетов
 * */

class UpdateBillUseCase (
    val apiRepository: BillRepository
) {

    suspend operator fun invoke(
        id: Int,
        newBill: UpdateAccountDto
    ): Result<AccountBriefModel> {

        return retryRequest {
            val account = apiRepository.updateBill(
                id = id,
                newBill = newBill
            )

            account
        }
    }

}
