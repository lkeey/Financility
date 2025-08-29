package dev.lkey.feature_splash.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.lkey.core.di.utils.ViewModelKey
import dev.lkey.feature_splash.presentation.viewmodel.SplashViewModel

/**
 * Модуль VM сплеш
 * */

@Module
interface SplashViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

}
