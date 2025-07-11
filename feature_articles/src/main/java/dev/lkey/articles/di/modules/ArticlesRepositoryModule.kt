package dev.lkey.articles.di.modules

import dagger.Module
import dagger.Provides
import dev.lkey.articles.data.dao.CategoryDao
import dev.lkey.articles.data.sync.ArticlesSyncStorage
import dev.lkey.articles.domain.repository.ArticlesRepository
import dev.lkey.articles.domain.repository.ArticlesRepositoryImpl

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
