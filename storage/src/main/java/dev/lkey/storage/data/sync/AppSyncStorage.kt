package dev.lkey.storage.data.sync

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.core.content.edit
import com.google.gson.Gson
import dev.lkey.common.core.model.AppInfo
import dev.lkey.common.core.model.HapticEffect
import dev.lkey.common.core.model.HapticSettings
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
        private const val COLOR_KEY = "primary_color"
        private const val INFO_KEY = "info_app"
        private const val HAPTICS_KEY = "haptics_enabled"
        private const val EFFECT_KEY = "haptics_effect"
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

    // primary color

    fun savePrimaryColor(color: Int) {
        prefs.edit {
            putInt(COLOR_KEY, color).apply()
        }
    }

    fun getPrimaryColor(): Int? {
        val color = prefs.getInt(COLOR_KEY, 0)

        return if (color == 0) null else color
    }

    // version
    fun saveAppInfo(info: AppInfo) {
        val json = Gson().toJson(info)

        prefs.edit {
            putString(INFO_KEY, json)
        }
    }

    fun getAppInfo(): AppInfo? {
        val json = prefs.getString(INFO_KEY, null) ?: return null

        return Gson().fromJson(json, AppInfo::class.java)
    }

    // haptics
    fun saveHapticsEnabled(enabled: Boolean) {
        prefs.edit {
            putBoolean(HAPTICS_KEY, enabled)
        }
    }

    fun saveEffect(effect: HapticEffect) {
        prefs.edit {
            putString(EFFECT_KEY, effect.name)
        }
    }

    fun loadHaptics(): HapticSettings {
        val enabled = prefs.getBoolean(HAPTICS_KEY, true)
        val effectName = prefs.getString(EFFECT_KEY, HapticEffect.CLICK.name)

        val effect = runCatching {
            HapticEffect.valueOf(effectName!!)
        }.getOrElse { HapticEffect.CLICK }

        return HapticSettings(enabled, effect)
    }
}
