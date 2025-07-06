package dev.lkey.financility.feature_transactions.domain.usecase

import dev.lkey.core.network.retryRequest
import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel
import dev.lkey.financility.feature_transactions.domain.repository.TransactionsRepository

/**
 * Use Case для получения счетов пользователя
 *
 * TODO: доработать локальное сохранение
 * */

class GetAccountUseCase (
//    val localRepository: AccountRepositoryImpl,
    val apiRepository: TransactionsRepository
) {

    suspend operator fun invoke(): Result<List<AccountBriefModel>> {

        /**
         * нельзя использоваться, так как счет в таком случае будет всегда браться из кеша
        * */

//        localRepository.getAccounts()?.let {
//            return Result.success(it)
//        }

        return retryRequest {
            val accounts = apiRepository.getAccounts()

//            accounts.onSuccess {
//                localRepository.saveAccounts(it)
//            }

            accounts
        }
    }
}
