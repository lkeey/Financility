package dev.lkey.articles.di.modules

import dagger.Module
import dagger.Provides
import dev.lkey.articles.domain.repository.ArticlesRepository
import dev.lkey.articles.domain.usecase.GetArticlesUseCase

@Module
class ArticlesUseCaseModule {

    @Provides
    fun provideArticlesUseCase(
        articlesRepository: ArticlesRepository
    ): GetArticlesUseCase {
        return GetArticlesUseCase(articlesRepository)
    }

}
