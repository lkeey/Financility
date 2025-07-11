package dev.lkey.articles.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CategoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ArticlesDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}
