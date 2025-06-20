package dev.lkey.financility.feature_articles.presentation

import dev.lkey.financility.core.network.FinancilityResult
import dev.lkey.financility.feature_transactions.domain.model.CategoryModel

data class ArticlesState (
    val articles: List<CategoryModel> = emptyList(),
    val searchValue: String = "",
    val status: FinancilityResult = FinancilityResult.Loading,
)
