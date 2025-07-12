package dev.lkey.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.lkey.storage.data.dao.CategoryDao
import dev.lkey.storage.data.model.CategoryEntity

@Database(
    entities = [CategoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}
