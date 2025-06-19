package dev.lkey.financility.feature_transactions.presentation.income.history.ui

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
import dev.lkey.financility.components.FinancilityBottomBar
import dev.lkey.financility.components.FinancilityLoadingBar
import dev.lkey.financility.components.FinancilitySnackBar
import dev.lkey.financility.components.FinancilityTopBar
import dev.lkey.financility.feature_transactions.presentation.expenses.today.ui.ExpensesView
import dev.lkey.financility.feature_transactions.presentation.income.history.HistoryIncomeAction
import dev.lkey.financility.feature_transactions.presentation.income.history.HistoryIncomeEvent
import dev.lkey.financility.feature_transactions.presentation.income.history.HistoryIncomeViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HistoryIncomeScreen(
    viewModel: HistoryIncomeViewModel = HistoryIncomeViewModel(),
    navController: NavController
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.onEvent(HistoryIncomeEvent.OnLoadIncomes)

        viewModel.action.collectLatest { action ->
            when (action) {
                is HistoryIncomeAction.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(action.message)
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

        HistoryIncomeView(
            modifier = Modifier.padding(padding),
            state = state
        ) {
            viewModel.onEvent(it)
        }

    }
}
