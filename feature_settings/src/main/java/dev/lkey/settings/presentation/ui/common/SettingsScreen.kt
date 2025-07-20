package dev.lkey.settings.presentation.ui.common

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkey.common.ui.item.FinancilitySnackBar
import dev.lkey.common.ui.nav.FinancilityBottomBar
import dev.lkey.common.ui.nav.FinancilityTopBar
import dev.lkey.settings.R
import dev.lkey.settings.presentation.viewmodel.SettingsAction
import dev.lkey.settings.presentation.viewmodel.SettingsEvent
import dev.lkey.settings.presentation.viewmodel.SettingsViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SettingsScreen (
    navController: NavController,
    viewModel: SettingsViewModel,
) {
    var error: String? by remember {
        mutableStateOf(null)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    val context = LocalContext.current
    val activity = context as? Activity

    LaunchedEffect(Unit) {
        viewModel.onEvent(SettingsEvent.OnLoadData)

        viewModel.action.collectLatest { action ->
            when (action) {
                is SettingsAction.ShowSnackBar -> {
                    error = action.message
                    snackBarHostState.showSnackbar(action.message)
                }

                SettingsAction.RestartActivity -> {
                    activity?.recreate()
                }
            }
        }
    }

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
        snackbarHost = { FinancilitySnackBar(snackBarHostState) }
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