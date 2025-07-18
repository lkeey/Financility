package dev.lkey.feature_splash.di.module

import dagger.Module
import dagger.Provides
import dev.lkey.account.domain.AccountRepository
import dev.lkey.articles.domain.ArticlesRepository
import dev.lkey.feature_splash.domain.GetAccountsUseCase
import dev.lkey.feature_splash.domain.GetArticlesUseCase

/**
 * Модуль use-case сплеш
 * */

@Module
class SplashUseCaseModule {

    @Provides
    fun provideGetAccountsUseCase (
        apiRepository: AccountRepository
    ): GetAccountsUseCase {
        return GetAccountsUseCase(apiRepository)
    }

    @Provides
    fun provideGetArticlesUseCase (
        repository: ArticlesRepository
    ): GetArticlesUseCase {
        return GetArticlesUseCase(repository)
    }

}
