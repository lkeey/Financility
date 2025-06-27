package dev.lkey.financility.feature_transactions.presentation.expenses.create.ui

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
import dev.lkey.financility.components.item.FinancilitySnackBar
import dev.lkey.financility.components.nav.FinancilityBottomBar
import dev.lkey.financility.components.nav.FinancilityTopBar
import dev.lkey.financility.feature_transactions.presentation.expenses.create.viewmodel.CreateExpensesAction
import dev.lkey.financility.feature_transactions.presentation.expenses.create.viewmodel.CreateExpensesEvent
import dev.lkey.financility.feature_transactions.presentation.expenses.create.viewmodel.CreateExpensesViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateExpensesScreen (
    viewModel: CreateExpensesViewModel = koinViewModel<CreateExpensesViewModel>(),
    navController: NavController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.onEvent(CreateExpensesEvent.OnLoadData)

        viewModel.action.collectLatest { action ->
            when (action) {
                is CreateExpensesAction.ShowSnackBar -> {
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
                title = "Мои расходы",
                actions = {
                    IconButton(
                        onClick = {
//                            /* TODO */
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_check),
                            contentDescription = "Сохранить",
                            tint = MaterialTheme.colorScheme.surfaceContainer
                        )
                    }
                },
                navIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_cross),
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

        CreateExpensesView (
            modifier = Modifier.padding(padding),
            state = state
        ) {
            viewModel.onEvent(it)
        }

    }
}
