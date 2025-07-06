package dev.lkey.transations.di

import dagger.Module
import dev.lkey.transations.di.module.TransactionsRepositoryModule
import dev.lkey.transations.di.module.TransactionsUseCaseModule
import dev.lkey.transations.di.module.TransactionsViewModelModule

@Module(
    includes = [
        TransactionsRepositoryModule::class,
        TransactionsUseCaseModule::class,
        TransactionsViewModelModule::class
    ]
)
class TransactionModules

