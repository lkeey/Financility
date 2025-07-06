package dev.lkey.articles.domain.repository

import dev.lkey.articles.data.model.CategoryModel

interface ArticlesRepository {

    suspend fun getArticles(): Result<List<CategoryModel>>

}
