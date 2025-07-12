package dev.lkey.transations.presentation.detail.ui

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
import dev.lkey.common.R
import dev.lkey.common.ui.item.FinancilityLoadingBar
import dev.lkey.common.ui.item.FinancilitySnackBar
import dev.lkey.common.ui.nav.FinancilityBottomBar
import dev.lkey.common.ui.nav.FinancilityTopBar
import dev.lkey.core.network.FinancilityResult
import dev.lkey.common.core.model.TransactionModel
import dev.lkey.transations.presentation.detail.viewmodel.UpdateTransactionAction
import dev.lkey.transations.presentation.detail.viewmodel.UpdateTransactionEvent
import dev.lkey.transations.presentation.detail.viewmodel.UpdateTransactionViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun UpdateTransactionScreen (
    viewModel: UpdateTransactionViewModel,
    navController: NavController,
    transaction: TransactionModel,
    isIncome : Boolean,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.onEvent(
            UpdateTransactionEvent.OnLoadData(
                isIncome = isIncome,
                transaction = transaction
            )
        )

        viewModel.action.collectLatest { action ->
            when (action) {
                is UpdateTransactionAction.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(action.message)
                }

                UpdateTransactionAction.OnOpenScreen -> {
                    navController.popBackStack()
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
                title = "Детали ${if (isIncome) "дохода" else "расхода"}",
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.onEvent(UpdateTransactionEvent.OnUpdate)
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

        if (state.status == FinancilityResult.Loading) {
            FinancilityLoadingBar(
                modifier = Modifier
                    .padding(padding)
            )
        } else {
            UpdateTransactionView (
                modifier = Modifier.padding(padding),
                state = state,
                isIncome = isIncome
            ) {
                viewModel.onEvent(it)
            }
        }

    }
}
