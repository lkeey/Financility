package dev.lkey.financility.feature_articles.presentation

import dev.lkey.financility.feature_transactions.domain.model.CategoryModel

data class ArticlesState (
    val articles: List<CategoryModel> = listOf(),
    val searchValue: String = "",
    val isLoading: Boolean = false,
)
