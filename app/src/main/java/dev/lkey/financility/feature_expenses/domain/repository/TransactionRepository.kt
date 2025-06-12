package dev.lkey.financility.feature_expenses.domain.repository

import dev.lkey.financility.feature_expenses.domain.model.TransactionModel

interface TransactionRepository {

    suspend fun getTodayTransactions(): List<TransactionModel>

}