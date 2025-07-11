package dev.lkey.articles.domain.usecase

import dev.lkey.articles.domain.repository.ArticlesRepository
import dev.lkey.common.core.model.CategoryModel
import dev.lkey.core.network.retryRequest
import jakarta.inject.Inject

/**
 * Use Case для получения статей
 * */

class GetArticlesUseCase @Inject constructor(
    private val repository: ArticlesRepository
) {

    suspend operator fun invoke(): Result<List<CategoryModel>> {
        return retryRequest {
            repository.getArticles()
        }
    }

}
