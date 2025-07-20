package dev.lkey.settings.presentation.ui.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkey.common.ui.nav.FinancilityBottomBar
import dev.lkey.common.ui.nav.FinancilityTopBar
import dev.lkey.settings.R
import dev.lkey.settings.presentation.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen (
    navController: NavController,
    viewModel: SettingsViewModel,
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold (
        bottomBar = {
            FinancilityBottomBar(
                navController = navController
            )
        },
        topBar = {
            FinancilityTopBar(
                title = stringResource(R.string.settings),
                actions = { }
            )
        },
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.onSurface,
    ) { padding ->

        SettingsView(
            modifier = Modifier.padding(padding),
            onNavigate = {
                navController.navigate(it)
            },
            state = state,
            onEvent = {
                viewModel.onEvent(it)
            }
        )

    }
}