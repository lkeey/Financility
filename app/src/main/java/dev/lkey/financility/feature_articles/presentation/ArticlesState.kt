package dev.lkey.financility.feature_articles.presentation

import dev.lkey.financility.feature_expenses.domain.model.CategoryModel

data class ArticlesState (
    val articles: List<CategoryModel> = listOf(),
    val searchValue: String = ""
)
