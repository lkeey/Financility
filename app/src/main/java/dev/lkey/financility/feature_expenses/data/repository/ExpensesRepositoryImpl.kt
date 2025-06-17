package dev.lkey.financility.feature_expenses.data.repository

import dev.lkey.financility.BuildConfig
import dev.lkey.financility.core.ApiException
import dev.lkey.financility.core.ktorClient
import dev.lkey.financility.core.safeCall
import dev.lkey.financility.feature_expenses.domain.model.TransactionModel
import dev.lkey.financility.feature_expenses.domain.repository.ExpensesRepository
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

class ExpensesRepositoryImpl : ExpensesRepository {

    override suspend fun getTodayExpenses(): Result<List<TransactionModel>> {
        return safeCall {
            val response: HttpResponse = ktorClient.get("${BuildConfig.BASE_URL}//transaction/list?from=1743465600,to=1743465699")

            if (response.status != HttpStatusCode.OK) {
                throw ApiException("Ошибка API: ${response.status}")
            }

            response.body()
        }
    }

//
//        val acc = AccountBriefModel(
//            id = "...",
//            name = "...",
//            balance = "...",
//            currency = "..."
//        )
//
//        return listOf(
//            TransactionModel(
//                id = 0,
//                account = acc,
//                categoryModel = CategoryModel(
//                    id = 0,
//                    name = "Аренда квартиры",
//                    emoji = "\uD83C\uDFE1",
//                    isIncome = false
//                ),
//                amount = "100 000 ₽",
//                transactionDate = "...",
//                comment = null,
//                createdAt = "...",
//                updatedAt = "...",
//            ),
//            TransactionModel(
//                id = 0,
//                account = acc,
//                categoryModel = CategoryModel(
//                    id = 0,
//                    name = "Одежда",
//                    emoji = "\uD83D\uDC57",
//                    isIncome = false
//                ),
//                amount = "100 000 ₽",
//                transactionDate = "...",
//                comment = null,
//                createdAt = "...",
//                updatedAt = "...",
//            ),
//            TransactionModel(
//                id = 0,
//                account = acc,
//                categoryModel = CategoryModel(
//                    id = 0,
//                    name = "На собачку",
//                    emoji = "\uD83D\uDC36",
//                    isIncome = false
//                ),
//                amount = "100 000 ₽",
//                transactionDate = "...",
//                comment = "Джек",
//                createdAt = "...",
//                updatedAt = "...",
//            ),
//            TransactionModel(
//                id = 0,
//                account = acc,
//                categoryModel = CategoryModel(
//                    id = 0,
//                    name = "На собачку",
//                    emoji = "\uD83D\uDC36",
//                    isIncome = false
//                ),
//                amount = "100 000 ₽",
//                transactionDate = "...",
//                comment = "Энни",
//                createdAt = "...",
//                updatedAt = "...",
//            ),
//            TransactionModel(
//                id = 0,
//                account = acc,
//                categoryModel = CategoryModel(
//                    id = 0,
//                    name = "Ремонт квартиры",
//                    emoji = "РК",
//                    isIncome = false
//                ),
//                amount = "100 000 ₽",
//                transactionDate = "...",
//                comment = null,
//                createdAt = "...",
//                updatedAt = "...",
//            ),
//            TransactionModel(
//                id = 0,
//                account = acc,
//                categoryModel = CategoryModel(
//                    id = 0,
//                    name = "Продукты",
//                    emoji = "\uD83C\uDF6D",
//                    isIncome = false
//                ),
//                amount = "100 000 ₽",
//                transactionDate = "...",
//                comment = null,
//                createdAt = "...",
//                updatedAt = "...",
//            ),
//            TransactionModel(
//                id = 0,
//                account = acc,
//                categoryModel = CategoryModel(
//                    id = 0,
//                    name = "Спортзал",
//                    emoji = "\uD83C\uDFCB\uFE0F",
//                    isIncome = false
//                ),
//                amount = "100 000 ₽",
//                transactionDate = "...",
//                comment = null,
//                createdAt = "...",
//                updatedAt = "...",
//            ),
//            TransactionModel(
//                id = 0,
//                account = acc,
//                categoryModel = CategoryModel(
//                    id = 0,
//                    name = "Медицина",
//                    emoji = "\uD83D\uDC8A",
//                    isIncome = false
//                ),
//                amount = "100 000 ₽",
//                transactionDate = "...",
//                comment = null,
//                createdAt = "...",
//                updatedAt = "...",
//            ),
//        )
//    }

}