package dev.lkey.financility.di.modules

import dagger.Module
import dagger.Provides
import dev.lkey.financility.feature_articles.data.repository.ArticlesRepositoryImpl
import dev.lkey.financility.feature_articles.domain.repository.ArticlesRepository

/**
 * Repository зависимости, инициализируемые Dagger-ом
 * */

@Module
class RepositoryModule {

    @Provides
    fun provideArticlesRepository(): ArticlesRepository {
        return ArticlesRepositoryImpl()
    }

}