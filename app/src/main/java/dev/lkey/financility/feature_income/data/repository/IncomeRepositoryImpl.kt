package dev.lkey.financility.feature_income.data.repository

import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel
import dev.lkey.financility.feature_expenses.domain.model.CategoryModel
import dev.lkey.financility.feature_expenses.domain.model.TransactionModel
import dev.lkey.financility.feature_income.domain.repository.IncomeRepository

class IncomeRepositoryImpl : IncomeRepository {
    override suspend fun getTodayIncome(): List<TransactionModel> {
        /* TODO API */

        val acc = AccountBriefModel(
            id = 0,
            name = "...",
            balance = "...",
            currency = "..."
        )

        return listOf(
            TransactionModel(
                id = 0,
                account = acc,
                categoryModel = CategoryModel(
                    id = 0,
                    name = "Зарплата",
                    emoji = "null",
                    isIncome = true
                ),
                amount = "500 000 ₽",
                transactionDate = "...",
                comment = null,
                createdAt = "...",
                updatedAt = "...",
            ),
            TransactionModel(
                id = 0,
                account = acc,
                categoryModel = CategoryModel(
                    id = 0,
                    name = "Подработка",
                    emoji = "null",
                    isIncome = true
                ),
                amount = "100 000 ₽",
                transactionDate = "...",
                comment = null,
                createdAt = "...",
                updatedAt = "...",
            ),
        )
    }
}