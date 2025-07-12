package dev.lkey.bill.di.modules

import dagger.Module
import dagger.Provides
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
    fun provideBillRepository(
        accountDao: AccountDao,
        appSyncStorage: AppSyncStorage
    ): BillRepository {
        return BillRepositoryImpl(
            accountDao = accountDao,
            appSyncStorage = appSyncStorage
        )
    }

}
