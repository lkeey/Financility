package dev.lkey.articles.di.modules

import dagger.Module
import dagger.Provides
import dev.lkey.articles.domain.repository.ArticlesRepository
import dev.lkey.articles.repository.ArticlesRepositoryImpl

/**
 * Модуль репозиториев статей расходов, инициализируемая Dagger-ом
 * */


@Module
class ArticlesRepositoryModule {

    @Provides
    fun provideArticlesRepository(): ArticlesRepository {
        return ArticlesRepositoryImpl()
    }

}
