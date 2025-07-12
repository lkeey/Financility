package dev.lkey.account.domain

import dev.lkey.common.core.model.AccountBriefModel

interface AccountRepository {

    suspend fun getAccounts(): Result<List<AccountBriefModel>>

    suspend fun getCashedAccounts(): Result<List<AccountBriefModel>>

}
