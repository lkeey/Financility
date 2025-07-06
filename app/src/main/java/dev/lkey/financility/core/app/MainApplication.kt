package dev.lkey.financility.core.app

import android.app.Application
import dev.lkey.financility.di.AppComponent
import dev.lkey.financility.di.DaggerAppComponent

/**
 * Класс приложения, инициализирующий зависимостями через Dagger
 *
 * При запуске приложения запускает Dagger и передаёт контекст приложения и модуль зависимостей [appModule]
 */

class MainApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory()
            .create(this)
    }
}