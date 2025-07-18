package dev.lkey.storage.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dev.lkey.storage.data.sync.AppSyncStorage

@Module
class ArticlesSyncModule {

    @Provides
    fun provideSyncStorage(context: Context): AppSyncStorage {
        return AppSyncStorage(context)
    }

}
