package dev.lkey.financility.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dev.lkey.financility.MainActivity
import dev.lkey.financility.di.modules.RepositoryModule
import dev.lkey.financility.di.modules.UseCaseModule
import dev.lkey.financility.di.modules.ViewModelModule
import jakarta.inject.Singleton

@Singleton
@Component(
    modules = [
//        ArticlesRepositoryModule::class,
//        ArticlesUseCaseModule::class,
//        ArticlesViewModelModule::class,

        RepositoryModule::class,
        UseCaseModule::class,
        ViewModelModule::class]
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
