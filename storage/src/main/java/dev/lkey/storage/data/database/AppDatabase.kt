package dev.lkey.storage.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.lkey.storage.data.dao.AccountDao
import dev.lkey.storage.data.dao.CategoryDao
import dev.lkey.storage.data.model.AccountEntity
import dev.lkey.storage.data.model.CategoryEntity

@Database(
    entities = [
        CategoryEntity::class,
        AccountEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    abstract fun accountDao(): AccountDao

}