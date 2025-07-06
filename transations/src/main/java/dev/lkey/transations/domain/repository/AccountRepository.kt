package dev.lkey.transations.domain.repository

import dev.lkey.common.core.model.AccountBriefModel

interface AccountRepository {

    fun getAccounts(): List<AccountBriefModel>?

    fun saveAccounts(
        accounts: List<AccountBriefModel>
    )

    fun clearCache()

}
