package dev.lkey.financility

import android.app.Application
import dev.lkey.core.di.CoreComponent
import dev.lkey.core.di.DaggerCoreComponent
import dev.lkey.core.di.utils.CoreProvider
import dev.lkey.financility.worker.scheduleSyncDataWorker

/**
 * При запуске приложения запускает Dagger и инициазизирует [coreComponent]
 * [coreComponent] - нужен для инъекции VM фабрики
 */

class MainApplication : Application(), CoreProvider {

    override val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()

        scheduleSyncDataWorker(this)
    }
}