package dev.lkey.bill.di.modules

import dagger.Module
import dagger.Provides
import dev.lkey.bill.data.repository.BillRepositoryImpl
import dev.lkey.bill.domain.repository.BillRepository

@Module
class BillRepositoryModule {

    @Provides
    fun provideBillRepository(): BillRepository {
        return BillRepositoryImpl()
    }

}
