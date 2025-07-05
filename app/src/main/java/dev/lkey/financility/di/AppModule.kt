package dev.lkey.financility.di

import dagger.Module
import dagger.Provides
import dev.lkey.financility.feature_articles.data.repository.ArticlesRepositoryImpl
import dev.lkey.financility.feature_articles.domain.repository.ArticlesRepository
import dev.lkey.financility.feature_articles.domain.usecase.GetArticlesUseCase

/**
 * Зависимости, инициализируемые Koin-ом
 * */

@Module
class AppModule {

    @Provides
    fun provideArticlesRepository(): ArticlesRepository {
        return ArticlesRepositoryImpl()
    }

    @Provides
    fun provideArticlesUseCase(
        articlesRepository: ArticlesRepository
    ): GetArticlesUseCase {
        return GetArticlesUseCase(articlesRepository)
    }

}
