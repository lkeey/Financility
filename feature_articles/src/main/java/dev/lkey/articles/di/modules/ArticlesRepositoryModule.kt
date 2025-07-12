package dev.lkey.articles.di.modules

import dagger.Module
import dagger.Provides
import dev.lkey.articles.data.sync.ArticlesSyncStorage
import dev.lkey.articles.domain.repository.ArticlesRepository
import dev.lkey.articles.domain.repository.ArticlesRepositoryImpl
import dev.lkey.storage.data.dao.CategoryDao

/**
 * Модуль репозиториев статей
 * */

@Module
class ArticlesRepositoryModule {

    @Provides
    fun provideArticlesRepository(
        categoryDao: CategoryDao,
        syncStorage: ArticlesSyncStorage
    ): ArticlesRepository {
        return ArticlesRepositoryImpl(
            categoryDao = categoryDao,
            articlesSyncStorage = syncStorage
        )
    }

}
