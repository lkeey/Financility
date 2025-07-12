package dev.lkey.storage.data.mappers.transaction

import dev.lkey.common.core.model.TransactionModel
import dev.lkey.storage.data.model.TransactionEntity

fun TransactionModel.toTransactionEntity(
    isSynced : Boolean
): TransactionEntity {
    return TransactionEntity(
        id = id,
        accountId = account.id,
        categoryId = categoryModel.id,
        amount = amount,
        transactionDate = transactionDate,
        comment = comment,
        createdAt = createdAt,
        updatedAt = updatedAt,
        isSynced = isSynced
    )
}
