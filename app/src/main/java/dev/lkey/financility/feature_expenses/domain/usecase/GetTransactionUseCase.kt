package dev.lkey.financility.feature_expenses.domain.usecase

import dev.lkey.financility.feature_expenses.data.repository.TransactionRepositoryImpl
import dev.lkey.financility.feature_expenses.domain.model.TransactionModel
import dev.lkey.financility.feature_expenses.domain.repository.TransactionRepository

class GetTransactionUseCase {

    private val repository: TransactionRepository = TransactionRepositoryImpl()

    suspend operator fun invoke(): List<TransactionModel> {
        return repository.getTodayTransactions()
    }

}