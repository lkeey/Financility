package dev.lkey.financility.feature_bill.domain.repository

import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel

interface GetBillInfoRepository {

    suspend fun getBillInfo(): Result<List<AccountBriefModel>>

}
