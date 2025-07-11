package dev.lkey.articles.repository

import dev.lkey.articles.domain.repository.ArticlesRepository
import dev.lkey.common.core.model.CategoryModel
import dev.lkey.core.error.ApiException
import dev.lkey.core.network.ktorClient
import dev.lkey.core.network.safeCall
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

/**
 * Репозиторий для получения статей
 * */

class ArticlesRepositoryImpl : ArticlesRepository {

    override suspend fun getArticles(): Result<List<CategoryModel>> {

        return safeCall {
            val response: HttpResponse = ktorClient.get("categories")

            if (response.status != HttpStatusCode.OK) {
                throw ApiException("Ошибка API: ${response.status}")
            }

            response.body()
        }
    }

}
