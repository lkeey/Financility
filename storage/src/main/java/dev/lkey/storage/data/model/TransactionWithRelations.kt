package dev.lkey.storage.data.model

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Модель транзакции, сразу предоставляющая модель категории и аккаунта
 */


data class TransactionWithRelations (
    @Embedded val transaction: TransactionEntity,

    @Relation(
        parentColumn = "accountId",
        entityColumn = "id"
    )
    val accountModel: AccountEntity,

    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val categoryModel: CategoryEntity

)
