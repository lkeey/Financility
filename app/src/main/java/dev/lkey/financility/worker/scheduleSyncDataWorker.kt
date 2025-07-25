package dev.lkey.financility.worker

import android.content.Context
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dev.lkey.storage.data.sync.AppSyncStorage
import java.util.concurrent.TimeUnit

fun scheduleSyncDataWorker(context: Context) {

    val syncer = AppSyncStorage(context = context)
    val duration = syncer.getSyncDuration()

    val workRequest = PeriodicWorkRequestBuilder<SyncDataWorker>(
        duration,
        TimeUnit.MINUTES
    )
        .setConstraints(
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        )
        .build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "sync_data",
        androidx.work.ExistingPeriodicWorkPolicy.KEEP,
        workRequest
    )
}
