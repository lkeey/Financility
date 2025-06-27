package dev.lkey.financility.feature_transactions.data.db

import android.content.Context
import androidx.core.content.edit
import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel
import dev.lkey.financility.feature_transactions.domain.repository.AccountRepository
import kotlinx.serialization.json.Json

/**
 * Репозиторий для локальной работы:
 * - с получением счетов
 * - с сохранением счетов
 * - с удалением счетов
 * */

class AccountRepositoryImpl(
    context: Context
) : AccountRepository {
    companion object {
        private const val PREF_ACCOUNTS_KEY = "cached_account"
    }

    private val preferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    private val json = Json { ignoreUnknownKeys = true }

    override fun getAccounts() : List<AccountBriefModel>? {
        val cachedJson = preferences.getString(PREF_ACCOUNTS_KEY, null) ?: return null

        return try {
            json.decodeFromString(cachedJson)
        } catch (_: Exception) {
            null
        }
    }

    override fun saveAccounts(
        accounts: List<AccountBriefModel>
    ) {
        val encoded = json.encodeToString(accounts)

        preferences.edit {
            putString(PREF_ACCOUNTS_KEY, encoded)
        }
    }

    override fun clearCache() {
        preferences.edit { remove(PREF_ACCOUNTS_KEY) }
    }

}
