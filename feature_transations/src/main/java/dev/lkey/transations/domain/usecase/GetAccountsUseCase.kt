package dev.lkey.transations.domain.usecase

import dev.lkey.account.domain.AccountRepository
import dev.lkey.common.core.model.AccountBriefModel
import dev.lkey.core.network.retryRequest

/**
 * Use Case для получения счетов пользователя
 */

class GetAccountsUseCase (
    val apiRepository: AccountRepository
) {

    suspend operator fun invoke(): Result<List<AccountBriefModel>> {

        return retryRequest {
            return@retryRequest apiRepository.getCashedAccounts()
        }

    }
}
