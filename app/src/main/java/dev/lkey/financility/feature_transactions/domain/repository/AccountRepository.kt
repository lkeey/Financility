package dev.lkey.financility.feature_transactions.domain.repository

import dev.lkey.common.core.model.AccountBriefModel

interface AccountRepository {

    fun getAccounts(): List<AccountBriefModel>?

    fun saveAccounts(
        accounts: List<AccountBriefModel>
    )

    fun clearCache()

}
