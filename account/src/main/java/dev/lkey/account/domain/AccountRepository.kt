package dev.lkey.account.domain

import dev.lkey.common.core.model.account.AccountBriefModel

interface AccountRepository {

    suspend fun getRemoteAccounts(): Result<List<AccountBriefModel>>

    suspend fun getCashedAccounts(): Result<List<AccountBriefModel>>

}
