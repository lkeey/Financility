package dev.lkey.transations.domain.usecase

import dev.lkey.common.core.model.AccountBriefModel
import dev.lkey.core.network.retryRequest
import dev.lkey.transations.domain.repository.TransactionsRepository

/**
 * Use Case для получения счетов пользователя
 */

class GetAccountUseCase (
    val apiRepository: TransactionsRepository
) {

    suspend operator fun invoke(): Result<List<AccountBriefModel>> {

        return retryRequest {
            val accounts = apiRepository.getAccounts()

            return@retryRequest accounts
        }
    }
}
