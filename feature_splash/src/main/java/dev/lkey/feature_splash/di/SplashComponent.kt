package dev.lkey.feature_splash.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import dev.lkey.core.di.CoreComponent
import dev.lkey.core.di.modules.SharedViewModelModule
import dev.lkey.feature_splash.di.module.SplashRepositoryModule
import dev.lkey.feature_splash.di.module.SplashUseCaseModule
import dev.lkey.feature_splash.di.module.SplashViewModelModule
import dev.lkey.storage.di.DatabaseComponent

/**
 * Компонент, который хранит в себе VM фичи сплеш
 */

@SplashScope
@Component(
    dependencies = [
        CoreComponent::class,
        DatabaseComponent::class,
    ],
    modules = [
        SplashRepositoryModule::class,
        SplashUseCaseModule::class,
        SplashViewModelModule::class,
        SharedViewModelModule::class
    ]
)
interface SplashComponent {

    fun viewModelFactory(): ViewModelProvider.Factory

    @Component.Factory
    interface Factory {
        fun create(
            core: CoreComponent,
            db: DatabaseComponent
        ): SplashComponent
    }

}