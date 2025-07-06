package dev.lkey.bill.di

import dagger.Module
import dev.lkey.bill.di.modules.BillRepositoryModule
import dev.lkey.bill.di.modules.BillUseCaseModule
import dev.lkey.bill.di.modules.BillViewModelModule

@Module(
    includes = [
        BillRepositoryModule::class,
        BillUseCaseModule::class,
        BillViewModelModule::class
    ]
)
class BillModules
