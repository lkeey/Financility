package dev.lkey.financility.feature_expenses.presentation.today.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkey.financility.R
import dev.lkey.financility.components.FinancilityBottomBar
import dev.lkey.financility.components.FinancilitySnackBar
import dev.lkey.financility.components.FinancilityTopBar
import dev.lkey.financility.feature_expenses.presentation.today.ExpensesAction
import dev.lkey.financility.feature_expenses.presentation.today.ExpensesEvent
import dev.lkey.financility.feature_expenses.presentation.today.ExpensesViewModel
import dev.lkey.financility.navigation.Route
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ExpensesScreen (
    viewModel: ExpensesViewModel = ExpensesViewModel(),
    navController: NavController
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.onEvent(ExpensesEvent.OnLoadTransactions)

        viewModel.action.collectLatest { action ->
            when (action) {
                is ExpensesAction.ShowSnackBar -> {
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
                title = "Расходы сегодня",
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Route.HistoryExpenses)
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_history),
                            contentDescription = "История",
                            tint = MaterialTheme.colorScheme.surfaceContainer
                        )
                    }
                }
            )
        },
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.onSurface,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Route.CreateExpenses)
                },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_plus),
                    contentDescription = "add button",
                    modifier = Modifier
                        .size(16.dp),
                    tint = Color.White
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        snackbarHost = { FinancilitySnackBar(snackBarHostState) }
    ) { padding ->

        ExpensesView(
            modifier = Modifier.padding(padding),
            state = state
        )

    }
}