package dev.lkey.transations.data.mappers

import dev.lkey.storage.data.model.TransactionEntity
import dev.lkey.transations.domain.model.TransactionModel

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
