package dev.lkey.financility.feature_bill.domain.repository

import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel

interface BillRepository {

    suspend fun getBillInfo(): Result<List<AccountBriefModel>>

    suspend fun updateBill(

    ): Result<AccountBriefModel>

}
