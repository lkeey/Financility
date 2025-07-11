package dev.lkey.financility.core.app

import android.app.Application
import dev.lkey.core.di.CoreComponent
import dev.lkey.core.di.DaggerCoreComponent
import dev.lkey.core.di.utils.CoreProvider

/**
 * Класс приложения, инициализирующий зависимостями через Dagger
 *
 * При запуске приложения запускает Dagger и передаёт контекст приложения и модуль зависимостей [appModule]
 */

class MainApplication : Application(), CoreProvider {

    override val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
    }
}
