package dev.lkey.articles.data

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.lkey.articles.data.dao.CategoryDao
import dev.lkey.articles.data.model.CategoryEntity

@Database(
    entities = [CategoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ArticlesDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}
