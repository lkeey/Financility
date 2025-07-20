package dev.lkey.financility

import android.app.Application
import android.content.Context
import dev.lkey.core.di.CoreComponent
import dev.lkey.core.di.DaggerCoreComponent
import dev.lkey.core.di.utils.CoreProvider
import dev.lkey.financility.worker.scheduleSyncDataWorker
import dev.lkey.storage.data.sync.AppSyncStorage

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

    // привязываем язык, выбранный в приложении
    override fun attachBaseContext(base: Context) {
        val syncer = AppSyncStorage(context = base)
        val context = syncer.setLocale(syncer.getSavedLocale())

        super.attachBaseContext(context)
    }
}