package dev.lkey.articles.di.modules

import dagger.Module
import dagger.Provides
import dev.lkey.articles.data.ArticlesRepositoryImpl
import dev.lkey.articles.domain.ArticlesRepository
import dev.lkey.storage.data.dao.CategoryDao
import dev.lkey.storage.data.sync.AppSyncStorage

/**
 * Модуль репозиториев статей
 * */

@Module
class ArticlesRepositoryModule {

    @Provides
    fun provideArticlesRepository(
        categoryDao: CategoryDao,
        syncStorage: AppSyncStorage
    ): ArticlesRepository {
        return ArticlesRepositoryImpl(
            categoryDao = categoryDao,
            appSyncStorage = syncStorage
        )
    }

}
