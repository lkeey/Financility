package dev.lkey.bill.domain.repository

import dev.lkey.bill.data.model.UpdateAccountDto
import dev.lkey.common.core.model.account.AccountBriefModel

interface BillRepository {

    suspend fun updateBill(
        id: Int,
        newBill : UpdateAccountDto
    ): Result<AccountBriefModel>

}
