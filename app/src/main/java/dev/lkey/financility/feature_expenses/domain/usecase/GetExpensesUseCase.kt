package dev.lkey.financility.feature_expenses.domain.usecase

import dev.lkey.financility.feature_expenses.data.repository.ExpensesRepositoryImpl
import dev.lkey.financility.feature_expenses.domain.model.TransactionModel
import dev.lkey.financility.feature_expenses.domain.repository.ExpensesRepository

class GetExpensesUseCase {

    private val repository: ExpensesRepository = ExpensesRepositoryImpl()

    suspend operator fun invoke(): List<TransactionModel> {
        return repository.getTodayExpenses()
    }

}