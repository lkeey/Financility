package dev.lkey.storage.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.lkey.storage.data.dao.AccountDao
import dev.lkey.storage.data.dao.CategoryDao
import dev.lkey.storage.data.dao.TransactionDao
import dev.lkey.storage.data.model.AccountEntity
import dev.lkey.storage.data.model.CategoryEntity
import dev.lkey.storage.data.model.TransactionEntity

@Database(
    entities = [
        CategoryEntity::class,
        AccountEntity::class,
        TransactionEntity::class,
    ],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    abstract fun accountDao(): AccountDao

    abstract fun transactionDao(): TransactionDao

}