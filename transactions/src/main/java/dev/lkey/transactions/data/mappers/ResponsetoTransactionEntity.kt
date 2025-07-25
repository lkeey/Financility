package dev.lkey.transactions.data.mappers

import dev.lkey.common.core.converter.convertMillisToDate
import dev.lkey.storage.data.model.TransactionEntity
import dev.lkey.transactions.data.dto.ResponseTransactionDto

fun ResponseTransactionDto.ResponsetoTransactionEntity(
    isSynced : Boolean
): TransactionEntity {
    val timeStamp = System.currentTimeMillis()

    return TransactionEntity(
        id = timeStamp.toInt(),
        accountId = accountId,
        categoryId = categoryId,
        amount = amount,
        transactionDate = transactionDate,
        comment = comment,
        createdAt = convertMillisToDate(timeStamp),
        updatedAt = convertMillisToDate(timeStamp),
        isSynced = isSynced
    )
}