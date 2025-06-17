package dev.lkey.financility.feature_articles.domain.usecase

import dev.lkey.financility.core.ApiException
import dev.lkey.financility.feature_articles.data.repository.ArticleRepositoryImpl
import dev.lkey.financility.feature_expenses.domain.model.CategoryModel
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.delay

class GetArticlesUseCase {

    private val repository: ArticleRepositoryImpl = ArticleRepositoryImpl()

    suspend operator fun invoke(): Result<List<CategoryModel>> {
        var attempt = 0
        val maxRetries = 3

        while (attempt < maxRetries) {
            try {
                return repository.getArticles()
            } catch (e: ServerResponseException) {
                if (e.response.status.value == 500) {
                    attempt++
                    if (attempt == maxRetries) throw e
                    delay(2000)
                } else {
                    throw e
                }
            } catch (e: Exception) {
                throw e
            }
        }

        throw ApiException("Не удалось выполнить запрос. Превышено количество попыток")
    }

}