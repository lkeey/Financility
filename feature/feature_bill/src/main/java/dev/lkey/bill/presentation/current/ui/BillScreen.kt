package dev.lkey.bill.presentation.current.ui

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.lkey.bill.presentation.current.viewmodel.BillAction
import dev.lkey.bill.presentation.current.viewmodel.BillEvent
import dev.lkey.bill.presentation.current.viewmodel.BillViewModel
import dev.lkey.common.R
import dev.lkey.common.navigation.Route
import dev.lkey.common.ui.item.FinancilityErrorMessage
import dev.lkey.common.ui.item.FinancilityLoadingBar
import dev.lkey.common.ui.item.FinancilitySnackBar
import dev.lkey.common.ui.nav.FinancilityBottomBar
import dev.lkey.common.ui.nav.FinancilityTopBar
import dev.lkey.core.network.FinancilityResult
import kotlinx.coroutines.flow.collectLatest

@Composable
fun BillScreen (
    navController: NavController,
    viewModel: BillViewModel
) {

    var error: String? by remember {
        mutableStateOf(null)
    }

    val state by viewModel.state.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.onEvent(BillEvent.OnLoadBill)

        viewModel.action.collectLatest { action ->
            when (action) {
                is BillAction.ShowSnackBar -> {
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
                title = stringResource(dev.lkey.bill.R.string.my_bill),
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Route.EditBill)
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_edit),
                            contentDescription = "изменить",
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

        when (state.status) {
            FinancilityResult.Error -> {
                FinancilityErrorMessage(
                    modifier = Modifier
                        .padding(padding),
                    text = error,
                    onUpdate = {
                        viewModel.onEvent(BillEvent.OnLoadBill)
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
                BillView(
                    modifier = Modifier.padding(padding),
                    state = state
                )
            }
        }

    }
}