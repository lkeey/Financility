package dev.lkey.articles.domain.repository

import dev.lkey.articles.data.mappers.toCategoryEntity
import dev.lkey.articles.data.mappers.toCategoryModel
import dev.lkey.articles.data.sync.ArticlesSyncStorage
import dev.lkey.common.core.model.CategoryModel
import dev.lkey.core.error.ApiException
import dev.lkey.core.error.OfflineDataException
import dev.lkey.core.network.ktorClient
import dev.lkey.core.network.safeCall
import dev.lkey.storage.data.dao.CategoryDao
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import jakarta.inject.Inject

/**
 * Репозиторий для получения статей
 * */

class ArticlesRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao,
    private val articlesSyncStorage: ArticlesSyncStorage
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

                /* save last sync */
                articlesSyncStorage.saveArticlesSyncTime(System.currentTimeMillis())

                return@safeCall articles

            } catch (e : Exception) {

                /* get cashed articles */
                val cached = categoryDao.getAll().map {
                    it.toCategoryModel()
                }

                /* if not cashed data */
                if (cached.isNotEmpty()) {
                    throw OfflineDataException(cached)
                }

                throw e
            }
        }
    }

}
