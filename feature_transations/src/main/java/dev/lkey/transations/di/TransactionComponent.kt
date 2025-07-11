package dev.lkey.transations.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import dev.lkey.core.di.CoreComponent
import dev.lkey.core.di.modules.SharedViewModelModule
import dev.lkey.transations.di.module.TransactionsRepositoryModule
import dev.lkey.transations.di.module.TransactionsUseCaseModule
import dev.lkey.transations.di.module.TransactionsViewModelModule

/**
 * Компонент, который хранит в себе VM, репозитории и use-case фичи транзакций
 */

@TransactionScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [
        TransactionsRepositoryModule::class,
        TransactionsUseCaseModule::class,
        TransactionsViewModelModule::class,
        SharedViewModelModule::class
    ]
)
interface TransactionComponent {

    fun viewModelFactory(): ViewModelProvider.Factory

    @Component.Factory
    interface Factory {
        fun create(core: CoreComponent): TransactionComponent
    }

}

