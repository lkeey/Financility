package dev.lkey.financility

import android.app.Application
import dev.lkey.financility.di.AppComponent
import dev.lkey.financility.di.AppModule
import dev.lkey.financility.di.DaggerAppComponent

/**
 * Класс приложения, инициализирующий зависимостями через Koin
 *
 * При запуске приложения запускает Koin и передаёт контекст приложения и модуль зависимостей [appModule]
 */

class MainApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()

        appComponent.inject(this)
    }
}