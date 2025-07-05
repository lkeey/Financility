package dev.lkey.financility

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import dev.lkey.financility.core.app.FinancilityApp
import jakarta.inject.Inject

/**
 * Главный Actitivy приложения
 * */

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MainApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FinancilityApp(viewModelFactory = viewModelFactory)
        }
    }
}
