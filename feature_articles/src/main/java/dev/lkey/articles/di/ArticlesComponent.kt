package dev.lkey.articles.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import dev.lkey.articles.di.modules.ArticlesRepositoryModule
import dev.lkey.articles.di.modules.ArticlesUseCaseModule
import dev.lkey.articles.di.modules.ArticlesViewModelModule
import dev.lkey.core.di.CoreComponent
import dev.lkey.core.di.modules.SharedViewModelModule
import dev.lkey.storage.di.DatabaseComponent

/**
 * Компонент, который хранит в себе VM, репозитории и use-case фичи статей
 */

@ArticlesScope
@Component(
    dependencies = [
        CoreComponent::class,
        DatabaseComponent::class,
    ],
    modules = [
        ArticlesRepositoryModule::class,
        ArticlesUseCaseModule::class,
        ArticlesViewModelModule::class,
        SharedViewModelModule::class
    ]
)
interface ArticlesComponent {

    fun viewModelFactory(): ViewModelProvider.Factory

    @Component.Factory
    interface Factory {
        fun create(
            core: CoreComponent,
            db: DatabaseComponent
        ): ArticlesComponent
    }

}
