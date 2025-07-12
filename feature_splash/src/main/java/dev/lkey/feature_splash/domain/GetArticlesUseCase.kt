package dev.lkey.feature_splash.domain

import dev.lkey.articles.domain.ArticlesRepository
import dev.lkey.common.core.model.CategoryModel
import jakarta.inject.Inject

/**
 * Use Case для получения статей
 * */

class GetArticlesUseCase @Inject constructor(
    private val repository: ArticlesRepository
) {

    suspend operator fun invoke(): Result<List<CategoryModel>> {
        return repository.getArticles()
    }

}
