package dev.lkey.articles.domain.usecase

import dev.lkey.articles.data.model.CategoryModel
import dev.lkey.articles.domain.repository.ArticlesRepository
import jakarta.inject.Inject

/**
 * Use Case для получения статей
 * */

class GetArticlesUseCase @Inject constructor(
    private val repository: ArticlesRepository
) {

    suspend operator fun invoke(): Result<List<CategoryModel>> {

//        return retryRequest {
//
//        }

        return repository.getArticles()

    }

}
