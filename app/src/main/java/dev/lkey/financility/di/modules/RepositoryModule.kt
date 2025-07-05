package dev.lkey.financility.di.modules

import dagger.Module
import dagger.Provides
import dev.lkey.financility.feature_articles.data.repository.ArticlesRepositoryImpl
import dev.lkey.financility.feature_articles.domain.repository.ArticlesRepository
import dev.lkey.financility.feature_bill.data.repository.BillRepositoryImpl
import dev.lkey.financility.feature_bill.domain.repository.BillRepository

/**
 * Repository зависимости, инициализируемые Dagger-ом
 * */

@Module
class RepositoryModule {

    // feature articles
    @Provides
    fun provideArticlesRepository(): ArticlesRepository {
        return ArticlesRepositoryImpl()
    }

    // feature bill
    @Provides
    fun provideBillRepository(): BillRepository {
        return BillRepositoryImpl()
    }


}