package dev.lkey.financility.feature_transactions.domain.repository

import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel

interface AccountRepository {

    fun getAccounts(): List<AccountBriefModel>?

    fun saveAccounts(
        accounts: List<AccountBriefModel>
    )

    fun clearCache()

}
