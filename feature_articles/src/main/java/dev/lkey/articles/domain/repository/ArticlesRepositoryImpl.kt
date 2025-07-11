package dev.lkey.articles.domain.repository

import dev.lkey.articles.data.dao.CategoryDao
import dev.lkey.articles.data.mapper.toCategoryEntity
import dev.lkey.articles.data.mapper.toCategoryModel
import dev.lkey.common.core.model.CategoryModel
import dev.lkey.core.error.ApiException
import dev.lkey.core.network.ktorClient
import dev.lkey.core.network.safeCall
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import jakarta.inject.Inject

/**
 * Репозиторий для получения статей
 * */

class ArticlesRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao
): ArticlesRepository {

    override suspend fun getArticles(): Result<List<CategoryModel>> {

        return safeCall {
            try {

                val response: HttpResponse = ktorClient.get("categories")

                if (response.status != HttpStatusCode.OK) {
                    throw ApiException("Ошибка API: ${response.status}")
                }

                val articles = response.body<List<CategoryModel>>()

                /* save to local DB */
                categoryDao.insertAll(articles.map {
                    it.toCategoryEntity()
                })

                return@safeCall articles

            } catch (e : Exception) {

                /* get cashed articles */
                val cached = categoryDao.getAll().map {
                    it.toCategoryModel()
                }

                /* if not cashed data */
                if (cached.isNotEmpty()) return@safeCall cached

                throw e
            }
        }
    }

}
