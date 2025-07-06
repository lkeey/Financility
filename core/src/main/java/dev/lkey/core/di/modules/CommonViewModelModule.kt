package dev.lkey.core.di.modules

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dev.lkey.core.di.ViewModelFactory

/**
 * VM зависимости, инициализируемые Dagger-ом
 * */

@Module
abstract class CommonViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
