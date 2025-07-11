package dev.lkey.core.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component

/**
 * Компонент, которых хранит в себе зависимости, нужны для инициализации других компонентов
 */

@CoreScope
@Component
interface CoreComponent {

    fun context(): Context

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

}
