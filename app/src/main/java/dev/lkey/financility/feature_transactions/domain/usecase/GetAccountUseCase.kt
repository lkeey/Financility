package dev.lkey.financility.feature_transactions.domain.usecase

import dev.lkey.financility.core.network.retryRequest
import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel
import dev.lkey.financility.feature_transactions.data.repository.TransactionsRepositoryImpl
import dev.lkey.financility.feature_transactions.domain.repository.TransactionsRepository

class GetAccountUseCase {
    private val repository: TransactionsRepository = TransactionsRepositoryImpl()

    suspend operator fun invoke(): Result<List<AccountBriefModel>> {

        return retryRequest {
            repository.getAccount()
        }
    }
}
