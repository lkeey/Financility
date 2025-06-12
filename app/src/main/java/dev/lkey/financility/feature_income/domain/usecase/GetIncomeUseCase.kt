package dev.lkey.financility.feature_income.domain.usecase

import dev.lkey.financility.feature_expenses.domain.model.TransactionModel
import dev.lkey.financility.feature_income.data.repository.IncomeRepositoryImpl

class GetIncomeUseCase {

    private val repository = IncomeRepositoryImpl()

    suspend fun invoke() : List<TransactionModel> {
        return repository.getTodayIncome()
    }
}