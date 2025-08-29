package dev.lkey.feature_splash.di.module

import dagger.Module
import dagger.Provides
import dev.lkey.account.data.AccountRepositoryImpl
import dev.lkey.account.domain.AccountRepository
import dev.lkey.articles.data.ArticlesRepositoryImpl
import dev.lkey.articles.domain.ArticlesRepository
import dev.lkey.storage.data.dao.AccountDao
import dev.lkey.storage.data.dao.CategoryDao
import dev.lkey.storage.data.sync.AppSyncStorage

/**
 * Модуль репозиториев сплеш
 * */

@Module
class SplashRepositoryModule {

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

    @Provides
    fun provideAccountRepository(
        accountDao: AccountDao,
        appSyncStorage: AppSyncStorage
    ): AccountRepository {
        return AccountRepositoryImpl(
            accountDao = accountDao,
            appSyncStorage = appSyncStorage
        )
    }
}