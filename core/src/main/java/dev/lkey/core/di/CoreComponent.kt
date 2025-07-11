package dev.lkey.core.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@CoreScope
@Component
interface CoreComponent {

    fun context(): Context

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

}
