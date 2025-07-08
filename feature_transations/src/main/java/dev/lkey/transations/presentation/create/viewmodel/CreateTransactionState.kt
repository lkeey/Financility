package dev.lkey.transations.presentation.create.viewmodel

import dev.lkey.common.core.model.AccountBriefModel
import dev.lkey.common.core.model.CategoryModel
import dev.lkey.core.network.FinancilityResult
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * Состояние экрана добавления расходов
 * */

data class CreateTransactionState (
    val accounts : List<AccountBriefModel> = emptyList(),
    val article: CategoryModel? = null,
    val sum: String? = null,
    val articles: List<CategoryModel> = emptyList(),
    val date: String = LocalDate.now()
        .format(DateTimeFormatter.ISO_DATE),
    val time: String = LocalTime.now()
        .format(DateTimeFormatter.ofPattern("HH:mm")),
    val comment: String? = null,
    val status: FinancilityResult = FinancilityResult.Loading,
)
