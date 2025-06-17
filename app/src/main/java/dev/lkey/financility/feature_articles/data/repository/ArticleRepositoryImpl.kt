package dev.lkey.financility.feature_articles.data.repository

import dev.lkey.financility.BuildConfig
import dev.lkey.financility.core.network.ApiException
import dev.lkey.financility.core.network.ktorClient
import dev.lkey.financility.core.network.safeCall
import dev.lkey.financility.feature_articles.domain.repository.ArticlesRepository
import dev.lkey.financility.feature_expenses.domain.model.CategoryModel
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

class ArticleRepositoryImpl : ArticlesRepository {

    override suspend fun getArticles(): Result<List<CategoryModel>> {

        return safeCall {
            val response: HttpResponse = ktorClient.get("${BuildConfig.BASE_URL}/categories")

            if (response.status != HttpStatusCode.OK) {
                throw ApiException("Ошибка API: ${response.status}")
            }

            response.body()
        }
    }

}