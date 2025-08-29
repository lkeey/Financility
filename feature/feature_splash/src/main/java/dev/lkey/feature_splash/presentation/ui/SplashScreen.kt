package dev.lkey.feature_splash.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkey.common.navigation.Route
import dev.lkey.common.ui.item.FinancilityErrorMessage
import dev.lkey.common.ui.item.FinancilitySnackBar
import dev.lkey.core.network.FinancilityResult
import dev.lkey.feature_splash.presentation.viewmodel.SplashAction
import dev.lkey.feature_splash.presentation.viewmodel.SplashEvent
import dev.lkey.feature_splash.presentation.viewmodel.SplashViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreen (
    navController: NavController,
    viewModel : SplashViewModel
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.onEvent(SplashEvent.OnLoadData)

        viewModel.action.collectLatest { action ->
            when (action) {
                SplashAction.OnOpenMainScreen -> {
                    navController.navigate(Route.Expense) {
                        popUpTo(Route.Splash) {
                            inclusive = true
                        }
                    }
                }
                is SplashAction.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(action.message)
                }
            }
        }
    }

    Scaffold (
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.onSurface,
        snackbarHost = { FinancilitySnackBar(snackBarHostState) }
    ) { padding ->

        when (state.status) {
            FinancilityResult.Error -> {
                FinancilityErrorMessage(
                    text = "Не удалось загрузить данные",
                    onUpdate = {
                        viewModel.onEvent(SplashEvent.OnLoadData)
                    },
                    modifier = Modifier
                )
            }
            FinancilityResult.Loading -> {
                SplashView(
                    modifier = Modifier
                        .padding(padding)
                )
            }
            FinancilityResult.Success -> {
                if (state.isPin) {
                    PinView(
                        modifier = Modifier
                            .padding(padding),
                        state = state,
                        onEvent = {
                            viewModel.onEvent(it)
                        }
                    )
                } else {
                    LaunchedEffect(Unit) {
                        navController.navigate(Route.Expense) {
                            popUpTo(Route.Splash) {
                                inclusive = true
                            }
                        }
                    }
                }
            }
        }
    }
}
