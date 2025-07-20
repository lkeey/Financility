package dev.lkey.settings.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import dev.lkey.core.di.CoreComponent
import dev.lkey.core.di.modules.SharedViewModelModule
import dev.lkey.settings.di.modules.SettingsViewModelModule
import dev.lkey.storage.di.DatabaseComponent


@SettingsScope
@Component(
    dependencies = [
        CoreComponent::class,
        DatabaseComponent::class,
    ],
    modules = [
        SettingsViewModelModule::class,
        SharedViewModelModule::class
    ]
)
interface SettingsComponent {

    fun viewModelFactory(): ViewModelProvider.Factory

    @Component.Factory
    interface Factory {
        fun create(
            core: CoreComponent,
            db: DatabaseComponent
        ): SettingsComponent
    }

}
