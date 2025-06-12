package dev.lkey.financility.feature_expenses.data.repository

import dev.lkey.financility.feature_expenses.domain.model.AccountBriefModel
import dev.lkey.financility.feature_expenses.domain.model.CategoryModel
import dev.lkey.financility.feature_expenses.domain.model.TransactionModel
import dev.lkey.financility.feature_expenses.domain.repository.TransactionRepository

class TransactionRepositoryImpl : TransactionRepository {

    override suspend fun getTodayTransactions(): List<TransactionModel> {
        /* TODO API */

        val acc = AccountBriefModel(
            id = 0,
            name = "...",
            balance = "0",
            currency = "..."
        )

        return listOf(
            TransactionModel(
                id = 0,
                account = acc,
                categoryModel = CategoryModel(
                    id = 0,
                    name = "Аренда квартиры",
                    emoji = "\uD83C\uDFE1",
                    isIncome = true
                ),
                amount = "100 000 ₽",
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
                    name = "Одежда",
                    emoji = "\uD83D\uDC57",
                    isIncome = true
                ),
                amount = "100 000 ₽",
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
                    name = "На собачку",
                    emoji = "\uD83D\uDC36",
                    isIncome = true
                ),
                amount = "100 000 ₽",
                transactionDate = "...",
                comment = "Джек",
                createdAt = "...",
                updatedAt = "...",
            ),
            TransactionModel(
                id = 0,
                account = acc,
                categoryModel = CategoryModel(
                    id = 0,
                    name = "На собачку",
                    emoji = "\uD83D\uDC36",
                    isIncome = true
                ),
                amount = "100 000 ₽",
                transactionDate = "...",
                comment = "Энни",
                createdAt = "...",
                updatedAt = "...",
            ),
            TransactionModel(
                id = 0,
                account = acc,
                categoryModel = CategoryModel(
                    id = 0,
                    name = "Ремонт квартиры",
                    emoji = "РК",
                    isIncome = true
                ),
                amount = "100 000 ₽",
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
                    name = "Продукты",
                    emoji = "\uD83C\uDF6D",
                    isIncome = true
                ),
                amount = "100 000 ₽",
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
                    name = "Спортзал",
                    emoji = "\uD83C\uDFCB\uFE0F",
                    isIncome = true
                ),
                amount = "100 000 ₽",
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
                    name = "Медицина",
                    emoji = "\uD83D\uDC8A",
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