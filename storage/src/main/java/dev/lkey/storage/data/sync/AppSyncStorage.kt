package dev.lkey.storage.data.sync

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class AppSyncStorage @Inject constructor(
    context: Context
) {

    companion object {
        private const val SHARED_PREFS = "sync_prefs"
    }

    private val prefs: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

    fun saveSyncTime(
        timestamp: Long,
        feature: String
    ) {
        prefs.edit {
            putLong(feature, timestamp)
        }
    }

    fun getSyncTime(
        feature: String
    ): Long {
        return prefs.getLong(feature, 0L)
    }

}
