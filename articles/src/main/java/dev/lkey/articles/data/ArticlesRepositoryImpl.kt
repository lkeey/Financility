package dev.lkey.articles.data

import dev.lkey.articles.domain.ArticlesRepository
import dev.lkey.common.constants.Constants
import dev.lkey.common.core.model.category.CategoryModel
import dev.lkey.core.error.ApiException
import dev.lkey.core.error.OfflineDataException
import dev.lkey.core.network.ktorClient
import dev.lkey.core.network.safeCall
import dev.lkey.storage.data.dao.CategoryDao
import dev.lkey.storage.data.mappers.category.toCategoryEntity
import dev.lkey.storage.data.mappers.category.toCategoryModel
import dev.lkey.storage.data.sync.AppSyncStorage
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import jakarta.inject.Inject

/**
 * Репозиторий для получения статей
 * */

class ArticlesRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao,
    private val appSyncStorage: AppSyncStorage
): ArticlesRepository {

    override suspend fun getArticles(): Result<List<CategoryModel>> {

        return safeCall {
            try {

                val response = ktorClient.get("categories")

                if (response.status != HttpStatusCode.Companion.OK) {
                    throw ApiException("Ошибка API: ${response.status}")
                }

                val articles = response.body<List<CategoryModel>>()

                /* save to local DB */
                categoryDao.insertAll(articles.map {
                    it.toCategoryEntity()
                })

                /* save last sync */
                appSyncStorage.saveSyncTime(
                    feature = Constants.ARTICLES_SYNC,
                    timestamp = System.currentTimeMillis()
                )

                return@safeCall articles

            } catch (e: Exception) {

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