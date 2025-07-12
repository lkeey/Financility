package dev.lkey.storage.data.mappers

import dev.lkey.common.core.model.TransactionModel
import dev.lkey.storage.data.model.TransactionWithRelations

fun TransactionWithRelations.toTransactionModel(): TransactionModel {
    return TransactionModel(
        id = transaction.id,
        account = accountModel.toAccountBriefModel(),
        categoryModel = categoryModel.toCategoryModel(),
        amount = transaction.amount,
        transactionDate = transaction.transactionDate,
        comment = transaction.comment,
        createdAt = transaction.createdAt,
        updatedAt = transaction.updatedAt,
    )
}
