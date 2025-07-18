package dev.lkey.bill.di.modules

import dagger.Module
import dagger.Provides
import dev.lkey.account.data.AccountRepositoryImpl
import dev.lkey.account.domain.AccountRepository
import dev.lkey.bill.data.repository.BillRepositoryImpl
import dev.lkey.bill.domain.repository.BillRepository
import dev.lkey.storage.data.dao.AccountDao
import dev.lkey.storage.data.sync.AppSyncStorage

/**
 * Модуль репозиториев счета
 * */

@Module
class BillRepositoryModule {

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

    @Provides
    fun provideBillRepository(): BillRepository {
        return BillRepositoryImpl()
    }

}
