package dev.lkey.financility.di.modules

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dev.lkey.core.di.ViewModelFactory

/**
 * VM зависимости, инициализируемые Dagger-ом
 * */

@Module
abstract class ViewModelModule {

    // common
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


}
