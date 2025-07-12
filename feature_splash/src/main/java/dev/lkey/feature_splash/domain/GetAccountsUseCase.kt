package dev.lkey.feature_splash.domain

import dev.lkey.account.domain.AccountRepository
import dev.lkey.common.core.model.AccountBriefModel

/**
 * Use Case для получения счетов пользователя
 */

class GetAccountsUseCase (
    val apiRepository: AccountRepository
) {

    suspend operator fun invoke(): Result<List<AccountBriefModel>> {
        return apiRepository.getRemoteAccounts()
    }
}