package dev.lkey.settings.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.lkey.core.di.utils.ViewModelKey
import dev.lkey.settings.presentation.viewmodel.SettingsViewModel

@Module
interface SettingsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    fun bindSettingsViewModel(viewModel: SettingsViewModel): ViewModel

}
