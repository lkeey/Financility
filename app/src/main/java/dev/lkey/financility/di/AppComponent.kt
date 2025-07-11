package dev.lkey.financility.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dev.lkey.bill.di.BillModules
import dev.lkey.core.di.modules.CommonViewModelModule
import dev.lkey.financility.MainActivity
import dev.lkey.transations.di.TransactionModules
import jakarta.inject.Singleton

@Singleton
@Component(
    modules = [
        CommonViewModelModule::class,
//        ArticlesComponent::class,
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
