package dev.lkey.financility.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dev.lkey.articles.di.ArticlesModules
import dev.lkey.financility.MainActivity
import dev.lkey.financility.di.modules.RepositoryModule
import dev.lkey.financility.di.modules.UseCaseModule
import dev.lkey.financility.di.modules.ViewModelModule
import jakarta.inject.Singleton

@Singleton
@Component(
    modules = [
        RepositoryModule::class,
        UseCaseModule::class,
        ViewModelModule::class,
        ArticlesModules::class
    ]
)
interface AppComponent {

    fun inject(application: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ) : AppComponent
    }

}
