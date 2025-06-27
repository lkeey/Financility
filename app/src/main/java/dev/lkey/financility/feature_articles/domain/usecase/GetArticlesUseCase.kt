package dev.lkey.financility.feature_articles.domain.usecase

import dev.lkey.financility.core.network.retryRequest
import dev.lkey.financility.feature_articles.data.repository.ArticleRepositoryImpl
import dev.lkey.financility.feature_transactions.domain.model.CategoryModel

/**
 * Use Case для получения статей
 * */

class GetArticlesUseCase (
    private val repository: ArticleRepositoryImpl
) {

    suspend operator fun invoke(): Result<List<CategoryModel>> {

        return retryRequest {
            repository.getArticles()
        }

    }

}