package dev.lkey.core.di.modules

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dev.lkey.core.di.utils.ViewModelFactory

/**
 * Общий модуль фабрики VM
 * */

@Module
interface SharedViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
