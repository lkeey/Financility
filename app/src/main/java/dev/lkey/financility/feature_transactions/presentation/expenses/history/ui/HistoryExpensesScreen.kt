package dev.lkey.financility.feature_transactions.presentation.expenses.history.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkey.financility.R
import dev.lkey.financility.components.item.FinancilityLoadingBar
import dev.lkey.financility.components.item.FinancilitySnackBar
import dev.lkey.financility.components.nav.FinancilityBottomBar
import dev.lkey.financility.components.nav.FinancilityTopBar
import dev.lkey.financility.core.network.FinancilityResult
import dev.lkey.financility.feature_transactions.presentation.expenses.history.viewmodel.HistoryExpensesAction
import dev.lkey.financility.feature_transactions.presentation.expenses.history.viewmodel.HistoryExpensesEvent
import dev.lkey.financility.feature_transactions.presentation.expenses.history.viewmodel.HistoryExpensesViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HistoryExpensesScreen (
    viewModel: HistoryExpensesViewModel,
    navController: NavController
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(true) {
        viewModel.onEvent(HistoryExpensesEvent.OnLoadExpenses)

        viewModel.action.collectLatest { event ->
            when (event) {
                is HistoryExpensesAction.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(event.message)
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
                title = "Моя история",
                actions = {
                    IconButton(
                        onClick = { /* TODO */ }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_statistics),
                            contentDescription = "Статистика",
                            tint = MaterialTheme.colorScheme.surfaceContainer
                        )
                    }
                },
                navIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_btn_back),
                            contentDescription = "Назад",
                            tint = MaterialTheme.colorScheme.surfaceContainer
                        )
                    }
                }
            )
        },
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.onSurface,
        snackbarHost = { FinancilitySnackBar(snackBarHostState) }
    ) { padding ->

        if (state.status != FinancilityResult.Success) {
            FinancilityLoadingBar(
                modifier = Modifier
                    .padding(padding)
            )
        } else {
            HistoryExpensesView(
                modifier = Modifier.padding(padding),
                state = state
            ) {
                viewModel.onEvent(it)
            }
        }

    }
}
