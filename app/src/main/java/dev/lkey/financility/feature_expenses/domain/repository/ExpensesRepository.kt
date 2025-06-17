package dev.lkey.financility.feature_expenses.domain.repository

import dev.lkey.financility.feature_expenses.domain.model.TransactionModel

interface ExpensesRepository {

    suspend fun getTodayExpenses(): Result<List<TransactionModel>>

}