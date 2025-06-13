package dev.lkey.financility.feature_income.domain.repository

import dev.lkey.financility.feature_expenses.domain.model.TransactionModel

interface IncomeRepository {

    suspend fun getTodayIncome(): List<TransactionModel>

}
