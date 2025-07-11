package dev.lkey.articles.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dev.lkey.articles.data.ArticlesDatabase
import dev.lkey.articles.data.CategoryDao

@Module
class RoomModule {

    @Provides
    fun provideDatabase(context: Context): ArticlesDatabase {
        return Room.databaseBuilder(
            context,
            ArticlesDatabase::class.java,
            "articles.db"
        ).build()
    }

    @Provides
    fun provideCategoryDao(db: ArticlesDatabase): CategoryDao = db.categoryDao()
}
