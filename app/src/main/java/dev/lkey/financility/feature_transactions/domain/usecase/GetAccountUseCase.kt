package dev.lkey.financility.feature_transactions.domain.usecase

import dev.lkey.financility.core.network.retryRequest
import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel
import dev.lkey.financility.feature_transactions.data.db.AccountRepositoryImpl
import dev.lkey.financility.feature_transactions.domain.repository.TransactionsRepository

class GetAccountUseCase (
    val localRepository: AccountRepositoryImpl,
    val apiRepository: TransactionsRepository
) {

//    private val apiRepository: TransactionsRepository = TransactionsRepositoryImpl()
//    private val localRepository: AccountRepositoryImpl = AccountRepositoryImpl(context = context)

    suspend operator fun invoke(): Result<List<AccountBriefModel>> {
        /* TODO */
//        println("load acc")
//
//        localRepository.getAccounts()?.let {
//            println("load acc $it")
//            return Result.success(it)
//        }
//
//        println("go to internet")

        return retryRequest {
            val accounts = apiRepository.getAccounts()

            accounts.onSuccess {
                localRepository.saveAccounts(it)
            }

            accounts
        }
    }
}
