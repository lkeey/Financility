package dev.lkey.articles.data.sync

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class ArticlesSyncStorage @Inject constructor(
    context: Context
) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("sync_prefs", Context.MODE_PRIVATE)

    fun saveArticlesSyncTime(timestamp: Long) {
        prefs.edit { putLong("articles_sync_time", timestamp) }
    }

    fun getArticlesSyncTime(): Long {
        return prefs.getLong("articles_sync_time", 0L)
    }
}
