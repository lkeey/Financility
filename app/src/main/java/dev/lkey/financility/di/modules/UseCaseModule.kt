package dev.lkey.financility.di.modules

import dagger.Module
import dagger.Provides
import dev.lkey.financility.feature_articles.domain.repository.ArticlesRepository
import dev.lkey.financility.feature_articles.domain.usecase.GetArticlesUseCase

/**
 * UseCase зависимости, инициализируемые Dagger-ом
 * */

@Module
class UseCaseModule {

    @Provides
    fun provideArticlesUseCase(
        articlesRepository: ArticlesRepository
    ): GetArticlesUseCase {
        return GetArticlesUseCase(articlesRepository)
    }
}
