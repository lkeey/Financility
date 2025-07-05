package dev.lkey.financility.di.modules

import dagger.Module
import dagger.Provides
import dev.lkey.financility.feature_articles.domain.repository.ArticlesRepository
import dev.lkey.financility.feature_articles.domain.usecase.GetArticlesUseCase
import dev.lkey.financility.feature_bill.domain.repository.BillRepository
import dev.lkey.financility.feature_bill.domain.usecase.GetBillInfoUseCase
import dev.lkey.financility.feature_bill.domain.usecase.UpdateBillUseCase

/**
 * UseCase зависимости, инициализируемые Dagger-ом
 * */

@Module
class UseCaseModule {

    // feature articles
    @Provides
    fun provideArticlesUseCase(
        articlesRepository: ArticlesRepository
    ): GetArticlesUseCase {
        return GetArticlesUseCase(articlesRepository)
    }

    // feature bill
    @Provides
    fun provideGetBillInfoUseCase(
        billRepository: BillRepository
    ): GetBillInfoUseCase {
        return GetBillInfoUseCase(billRepository)
    }

    @Provides
    fun provideUpdateBillUseCase(
        billRepository: BillRepository
    ): UpdateBillUseCase {
        return UpdateBillUseCase(billRepository)
    }

}
