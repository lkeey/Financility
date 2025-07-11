package dev.lkey.articles.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import dev.lkey.articles.di.modules.ArticlesRepositoryModule
import dev.lkey.articles.di.modules.ArticlesUseCaseModule
import dev.lkey.articles.di.modules.ArticlesViewModelModule
import dev.lkey.core.di.modules.CommonViewModelModule

@Component(
    modules = [
        ArticlesRepositoryModule::class,
        ArticlesUseCaseModule::class,
        ArticlesViewModelModule::class,

        CommonViewModelModule::class
    ]
)
@ArticlesScope
interface ArticlesComponent {

    fun viewModelFactory(): ViewModelProvider.Factory

    @Component.Factory
    interface Factory {
        fun create(): ArticlesComponent
    }

}
