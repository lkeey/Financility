package dev.lkey.storage.data.sync

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.core.content.edit
import dev.lkey.common.theme.ThemeMode
import java.util.Locale
import javax.inject.Inject

class AppSyncStorage @Inject constructor(
    private val context: Context
) {

    companion object {
        private const val SHARED_PREFS = "sync_prefs"
        private const val SYNC_DURATION = "sync_duration"
        private const val LANGUAGE_KEY = "language"
        private const val THEME_KEY = "theme"
    }

    private val prefs: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

    // get last duration

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

    // Duration of syncing

    fun setSyncDuration(
        time: Long
    ) {
        prefs.edit {
            putLong(SYNC_DURATION, time)
        }
    }

    fun getSyncDuration(): Long {
        return prefs.getLong(SYNC_DURATION, 15L)
    }

    // locale language

    fun setLocale(
        language: String
    ): Context {
        persistLanguage(language)

        val locale = Locale(language)
        Locale.setDefault(locale)

        val config = Configuration()
        config.setLocale(locale)

        return context.createConfigurationContext(config)
    }

    fun getSavedLocale(): String {
        val prefs = context.getSharedPreferences(LANGUAGE_KEY, Context.MODE_PRIVATE)

        return prefs.getString(LANGUAGE_KEY, "ru") ?: "ru"
    }

    private fun persistLanguage(language: String) {
        val prefs = context.getSharedPreferences(LANGUAGE_KEY, Context.MODE_PRIVATE)

        prefs.edit {
            putString(LANGUAGE_KEY, language)
        }

    }

    // theme
    fun saveThemeMode(mode: ThemeMode) {
        prefs.edit {
            putString(THEME_KEY, mode.name).apply()
        }
    }

    fun getThemeMode(): ThemeMode {
        val name = prefs.getString(THEME_KEY, ThemeMode.SYSTEM.name)

        return ThemeMode.valueOf(name ?: ThemeMode.SYSTEM.name)
    }
}
