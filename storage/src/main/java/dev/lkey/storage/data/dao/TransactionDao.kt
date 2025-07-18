package dev.lkey.storage.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.lkey.storage.data.model.TransactionEntity
import dev.lkey.storage.data.model.TransactionWithRelations

@Dao
interface TransactionDao {

    @Query("SELECT * FROM transactions")
    suspend fun getAll(): List<TransactionWithRelations>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(transaction: List<TransactionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: TransactionEntity)

    @Query("DELETE FROM transactions WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM transactions WHERE isSynced = 0")
    suspend fun getUnsynced(): List<TransactionEntity>

    @Query("UPDATE transactions SET isSynced = 1 WHERE id = :id")
    suspend fun markAsSynced(id: Int)
}
