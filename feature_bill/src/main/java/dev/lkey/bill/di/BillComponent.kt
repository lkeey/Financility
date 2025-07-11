package dev.lkey.bill.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import dev.lkey.bill.di.modules.BillRepositoryModule
import dev.lkey.bill.di.modules.BillUseCaseModule
import dev.lkey.bill.di.modules.BillViewModelModule
import dev.lkey.core.di.CoreComponent
import dev.lkey.core.di.modules.SharedViewModelModule

/**
 * Компонент, который хранит в себе VM, репозитории и use-case фичи счета
 */

@BillScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [
        BillRepositoryModule::class,
        BillUseCaseModule::class,
        BillViewModelModule::class,
        SharedViewModelModule::class
    ]
)
interface BillComponent {

    fun viewModelFactory(): ViewModelProvider.Factory

    @Component.Factory
    interface Factory {
        fun create(core: CoreComponent): BillComponent
    }

}
