package dev.lkey.financility.feature_articles.domain.usecase

import dev.lkey.financility.feature_articles.data.repository.ArticleRepositoryImpl
import dev.lkey.financility.feature_expenses.domain.model.CategoryModel

class GetArticlesUseCase {

    private val repository: ArticleRepositoryImpl = ArticleRepositoryImpl()

    suspend operator fun invoke(): List<CategoryModel> {
        return repository.getArticles()
    }

}