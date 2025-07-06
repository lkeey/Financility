package dev.lkey.financility.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dev.lkey.articles.di.ArticlesModules
import dev.lkey.bill.di.BillModules
import dev.lkey.financility.MainActivity
import dev.lkey.financility.di.modules.ViewModelModule
import dev.lkey.transations.di.TransactionModules
import jakarta.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelModule::class,
        ArticlesModules::class,
        BillModules::class,
        TransactionModules::class
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
