package dev.lkey.articles.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dev.lkey.articles.data.sync.ArticlesSyncStorage

@Module
class ArticlesSyncModule {

    @Provides
    fun provideSyncStorage(context: Context): ArticlesSyncStorage {
        return ArticlesSyncStorage(context)
    }

}
