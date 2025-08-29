package dev.lkey.storage.data.encrypted

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import javax.inject.Inject

class EncryptedStorage @Inject constructor(
    context: Context
) {
    companion object {
        private const val ENCRYPTED_SHARED_PREFS = "encrypted_sync_prefs"
        private const val PIN_KEY = "user_pin"
    }

    private val prefs: SharedPreferences =
        EncryptedSharedPreferences.create(
            context,
            ENCRYPTED_SHARED_PREFS,
            MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    fun savePin(pin: String) {
        prefs.edit {
            putString(PIN_KEY, pin)
        }
    }

    fun getPin(): String? {
        return prefs.getString(PIN_KEY, null)
    }

    fun hasPin(): Boolean {
        return getPin() != null
    }

}
