package dev.lkey.articles.presentation.viewmodel

import dev.lkey.financility.core.network.FinancilityResult
import dev.lkey.financility.feature_transactions.domain.model.CategoryModel

/**
 * Состояние экрана статей
 * */

data class ArticlesState (
    val articles: List<CategoryModel> = emptyList(),
    val searchValue: String = "",
    val status: FinancilityResult = FinancilityResult.Loading,
)
