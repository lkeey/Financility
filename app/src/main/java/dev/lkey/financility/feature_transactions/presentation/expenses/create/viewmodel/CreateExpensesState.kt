package dev.lkey.financility.feature_transactions.presentation.expenses.create.viewmodel

import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel
import dev.lkey.financility.feature_transactions.domain.model.CategoryModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * Состояние экрана добавления расходов
 * */

data class CreateExpensesState (
    val accounts : List<AccountBriefModel> = emptyList(),
    val article: CategoryModel? = null,
    val sum: String? = null,
    val articles: List<CategoryModel> = emptyList(),
    val date: String = LocalDate.now()
        .format(DateTimeFormatter.ISO_DATE),
    val time: String = LocalTime.now()
        .format(DateTimeFormatter.ofPattern("HH:mm")),
    val comment: String? = null,
    val isLoading: Boolean = false,
)
