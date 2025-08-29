package dev.lkey.transactions.data.mappers

import dev.lkey.storage.data.model.TransactionEntity
import dev.lkey.transactions.data.dto.RequestTransactionDto

fun TransactionEntity.toTransactionDto(): RequestTransactionDto {

    return RequestTransactionDto(
        accountId = accountId,
        categoryId = categoryId,
        amount = amount,
        transactionDate = transactionDate,
        comment = comment,
    )
}