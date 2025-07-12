package dev.lkey.transations.data.local

import androidx.room.Entity
import dev.lkey.common.core.model.AccountBriefModel
import dev.lkey.common.core.model.CategoryModel

@Entity(tableName = "transactions")
data class TransactionEntity (
    val id: Int,
    val account: AccountBriefModel,
    val categoryModel: CategoryModel,
    val amount: String,
    val transactionDate: String,
    val comment: String?,
    val createdAt: String,
    val updatedAt: String,

    val isSynced: Boolean = false
)
