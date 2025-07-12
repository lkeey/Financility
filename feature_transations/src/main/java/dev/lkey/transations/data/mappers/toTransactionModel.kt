package dev.lkey.transations.data.mappers

import dev.lkey.storage.data.model.TransactionWithRelations
import dev.lkey.transations.domain.model.TransactionModel

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
