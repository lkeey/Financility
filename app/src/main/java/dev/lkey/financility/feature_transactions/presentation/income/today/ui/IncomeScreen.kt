package dev.lkey.financility.feature_transactions.presentation.income.today.ui

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.lkey.financility.R
import dev.lkey.financility.components.nav.FinancilityBottomBar
import dev.lkey.financility.components.item.FinancilityLoadingBar
import dev.lkey.financility.components.item.FinancilitySnackBar
import dev.lkey.financility.components.nav.FinancilityTopBar
import dev.lkey.financility.core.network.FinancilityResult
import dev.lkey.financility.feature_transactions.presentation.income.today.viewmodel.IncomeAction
import dev.lkey.financility.feature_transactions.presentation.income.today.viewmodel.IncomeEvent
import dev.lkey.financility.feature_transactions.presentation.income.today.viewmodel.IncomeViewModel
import dev.lkey.financility.navigation.util.Route
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun IncomeScreen (
    navController: NavController,
    viewModel: IncomeViewModel = koinViewModel<IncomeViewModel>()
) {

    val state by viewModel.state.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.onEvent(IncomeEvent.OnLoadTodayIncomes)

        viewModel.action.collectLatest { event ->
            when (event) {
                is IncomeAction.ShowSnackBar -> {
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
                title = "Доходы сегодня",
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Route.HistoryIncomes)
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
                onClick = { /* TODO */ },
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

        if (state.status != FinancilityResult.Success) {
            FinancilityLoadingBar(
                modifier = Modifier
                    .padding(padding)
            )
        } else {
            IncomeView(
                modifier = Modifier
                    .padding(padding),
                state = state
            )
        }

    }
}
