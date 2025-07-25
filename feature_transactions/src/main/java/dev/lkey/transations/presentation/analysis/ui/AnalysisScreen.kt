package dev.lkey.transations.presentation.analysis.ui

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkey.common.R
import dev.lkey.common.ui.item.FinancilityErrorMessage
import dev.lkey.common.ui.item.FinancilityLoadingBar
import dev.lkey.common.ui.item.FinancilitySnackBar
import dev.lkey.common.ui.nav.FinancilityBottomBar
import dev.lkey.common.ui.nav.FinancilityTopBar
import dev.lkey.core.network.FinancilityResult
import dev.lkey.transations.presentation.analysis.viewmodel.AnalysisAction
import dev.lkey.transations.presentation.analysis.viewmodel.AnalysisEvent
import dev.lkey.transations.presentation.analysis.viewmodel.AnalysisViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AnalysisScreen (
    viewModel: AnalysisViewModel,
    navController: NavController,
    isIncome : Boolean,
) {
    var error: String? by remember {
        mutableStateOf(null)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
    LaunchedEffect(true) {
        viewModel.onEvent(AnalysisEvent.OnLoadTransactions(
            isIncome = isIncome
        ))

        viewModel.action.collectLatest { action ->
            when (action) {
                is AnalysisAction.ShowSnackBar -> {
                    error = action.message

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
                title = stringResource(dev.lkey.transations.R.string.analysys),
                containerColor = MaterialTheme.colorScheme.onSurface,
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

        when (state.status) {
            FinancilityResult.Error -> {
                FinancilityErrorMessage(
                    modifier = Modifier
                        .padding(padding),
                    text = error,
                    onUpdate = {
                        viewModel.onEvent(AnalysisEvent.OnLoadTransactions(
                            isIncome = isIncome
                        ))
                    }
                )
            }
            FinancilityResult.Loading -> {
                FinancilityLoadingBar(
                    modifier = Modifier
                        .padding(padding)
                )
            }
            FinancilityResult.Success -> {

                AnalysisView (
                    modifier = Modifier
                        .padding(padding),
                    state = state,
                    isIncome = isIncome,
                    onEvent = {
                        viewModel.onEvent(it)
                    }
                )

            }
        }

    }

}
