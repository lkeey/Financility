package dev.lkey.articles.presentation.viewmodel

import dev.lkey.common.core.model.category.CategoryModel
import dev.lkey.core.network.FinancilityResult


/**
 * Состояние экрана статей
 * */

data class ArticlesState (
    val articles: List<CategoryModel> = emptyList(),
    val searchValue: String = "",
    val status: FinancilityResult = FinancilityResult.Loading,
    val lastSync: Long? = null,
)
