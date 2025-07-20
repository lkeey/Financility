package dev.lkey.financility

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import dev.lkey.financility.navigation.FinancilityApp
import dev.lkey.storage.data.sync.AppSyncStorage

/**
 * Главный Actitivy приложения
 * устанавливает навигацию и ui
 * */

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val syncer = AppSyncStorage(context = this)
        val color = syncer.getPrimaryColor()

        setContent {
            FinancilityApp(
                theme = syncer.getThemeMode(),
                primaryColor = if (color != null) Color(color) else Color(0xFF2AE881),
            )
        }
    }

    // привязываем язык, выбранный в приложении
    override fun attachBaseContext(base: Context) {

        val syncer = AppSyncStorage(context = base)
        val context = syncer.setLocale(syncer.getSavedLocale())
        Log.i("--APP", "язык - ${syncer.getSavedLocale()}")
        Log.i("--APP", "контекст - $context")

        super.attachBaseContext(context)
    }
}
