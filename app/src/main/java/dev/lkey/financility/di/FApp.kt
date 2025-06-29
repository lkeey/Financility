package dev.lkey.financility.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

/**
 * Класс приложения, инициализирующий зависимостями через Koin
 *
 * При запуске приложения запускает Koin и передаёт контекст приложения и модуль зависимостей [appModule]
 */

class FApp : Application() {
    override fun onCreate() {
        super.onCreate()

        GlobalContext.startKoin {
            androidContext(this@FApp)
            modules(appModule)
        }
    }
}
