package dev.lkey.financility.di

import dev.lkey.financility.feature_articles.di.articleModule
import dev.lkey.financility.feature_bill.di.billModule
import dev.lkey.financility.feature_transactions.di.transactionModule

/**
 * Зависимости, инициализируемые Koin-ом
 * */

val appModule = listOf(
    billModule,
    articleModule,
    transactionModule
)
