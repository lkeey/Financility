package dev.lkey.account.domain

import dev.lkey.common.core.model.AccountBriefModel

interface AccountRepository {

    suspend fun getBillInfo(): Result<List<AccountBriefModel>>

}
