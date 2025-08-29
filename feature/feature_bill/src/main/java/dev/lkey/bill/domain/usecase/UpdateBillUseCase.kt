package dev.lkey.bill.domain.usecase

import dev.lkey.bill.data.model.UpdateAccountDto
import dev.lkey.bill.domain.repository.BillRepository
import dev.lkey.common.core.model.account.AccountBriefModel
import dev.lkey.core.network.retryRequest
import jakarta.inject.Inject

/**
 * Use Case обновления счетов
 * */

class UpdateBillUseCase @Inject constructor(
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
