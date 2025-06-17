package dev.lkey.financility.feature_bill.data.repository

import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel
import dev.lkey.financility.feature_bill.domain.repository.GetBillInfoRepository

class GetBillInfoRepositoryImpl : GetBillInfoRepository {

    override suspend fun getBillInfo(): List<AccountBriefModel> {
        /* TODO API */

        return listOf(
            AccountBriefModel(
                id = 0,
                name = "Основной счет",
                balance = "-670 000",
                currency = "₽"
            )
        )
    }

}
