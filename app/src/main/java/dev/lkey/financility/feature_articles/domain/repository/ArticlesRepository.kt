package dev.lkey.financility.feature_articles.domain.repository

import dev.lkey.financility.feature_transactions.domain.model.CategoryModel

interface ArticlesRepository {

    suspend fun getArticles(): Result<List<CategoryModel>>

}
