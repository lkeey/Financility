package dev.lkey.storage.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dev.lkey.storage.data.encrypted.EncryptedStorage
import dev.lkey.storage.data.sync.AppSyncStorage

@Module
class ArticlesStorageModule {

    @Provides
    fun provideSyncStorage(context: Context): AppSyncStorage {
        return AppSyncStorage(context)
    }

    @Provides
    fun provideEncryptedStorage(context: Context): EncryptedStorage {
        return EncryptedStorage(context)
    }

}
