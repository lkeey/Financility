package dev.lkey.transations.data.mappers

import dev.lkey.storage.data.model.TransactionEntity
import dev.lkey.transations.data.dto.TransactionDto

fun TransactionEntity.toTransactionDto(): TransactionDto {

    return TransactionDto(
        accountId = accountId,
        categoryId = categoryId,
        amount = amount,
        transactionDate = transactionDate,
        comment = comment,
    )
}