package dev.lkey.bill.di.modules

import dagger.Module
import dagger.Provides
import dev.lkey.bill.domain.repository.BillRepository
import dev.lkey.bill.domain.usecase.GetBillInfoUseCase
import dev.lkey.bill.domain.usecase.UpdateBillUseCase

/**
 * Модуль use-case счета
 * */

@Module
class BillUseCaseModule {

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
