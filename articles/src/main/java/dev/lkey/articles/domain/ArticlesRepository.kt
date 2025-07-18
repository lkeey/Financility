package dev.lkey.articles.domain

import dev.lkey.common.core.model.CategoryModel

interface ArticlesRepository {
    suspend fun getArticles(): Result<List<CategoryModel>>
}
