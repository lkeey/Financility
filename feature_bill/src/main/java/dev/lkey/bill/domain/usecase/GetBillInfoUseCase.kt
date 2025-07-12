package dev.lkey.bill.domain.usecase

import dev.lkey.account.domain.AccountRepository
import dev.lkey.common.core.model.AccountBriefModel
import dev.lkey.core.network.retryRequest
import jakarta.inject.Inject

/**
 * Use Case получения счетов
 * */

class GetBillInfoUseCase @Inject constructor(
    val accountRepository: AccountRepository
) {
    suspend operator fun invoke(): Result<List<AccountBriefModel>> {

        return retryRequest {
            val accounts = accountRepository.getCashedAccounts()

            accounts
        }

    }
}
