package dev.lkey.storage.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dev.lkey.storage.AppDatabase
import dev.lkey.storage.data.dao.CategoryDao

@Module
class AppRoomModule {

    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "financility.db"
        ).build()
    }

    @Provides
    fun provideCategoryDao(db: AppDatabase): CategoryDao = db.categoryDao()

}
