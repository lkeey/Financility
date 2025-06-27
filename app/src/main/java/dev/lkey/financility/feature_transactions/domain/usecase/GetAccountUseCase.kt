package dev.lkey.financility.feature_transactions.domain.usecase

import dev.lkey.financility.core.network.retryRequest
import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel
import dev.lkey.financility.feature_transactions.data.db.AccountRepositoryImpl
import dev.lkey.financility.feature_transactions.domain.repository.TransactionsRepository

class GetAccountUseCase (
    val localRepository: AccountRepositoryImpl,
    val apiRepository: TransactionsRepository
) {

    suspend operator fun invoke(): Result<List<AccountBriefModel>> {

        localRepository.getAccounts()?.let {
            return Result.success(it)
        }

        return retryRequest {
            val accounts = apiRepository.getAccounts()

            accounts.onSuccess {
                localRepository.saveAccounts(it)
            }

            accounts
        }
    }
}
