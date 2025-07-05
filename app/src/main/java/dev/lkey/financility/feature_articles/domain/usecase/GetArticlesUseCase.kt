package dev.lkey.financility.feature_articles.domain.usecase

import dev.lkey.financility.core.network.retryRequest
import dev.lkey.financility.feature_articles.domain.repository.ArticlesRepository
import dev.lkey.financility.feature_transactions.domain.model.CategoryModel

/**
 * Use Case для получения статей
 * */

class GetArticlesUseCase (
    private val repository: ArticlesRepository
) {

    suspend operator fun invoke(): Result<List<CategoryModel>> {

        return retryRequest {
            repository.getArticles()
        }

    }

}