package dev.lkey.financility.feature_articles.data.api

import dev.lkey.financility.feature_transactions.domain.model.CategoryModel

interface ArticlesApi {

    suspend fun getArticles(): Result<List<CategoryModel>>

}
