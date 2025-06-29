package dev.lkey.financility.feature_bill.presentation.edit.ui

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.lkey.financility.R
import dev.lkey.financility.components.item.FinancilityLoadingBar
import dev.lkey.financility.components.nav.FinancilityBottomBar
import dev.lkey.financility.components.nav.FinancilityTopBar
import dev.lkey.financility.core.network.FinancilityResult
import dev.lkey.financility.feature_bill.presentation.current.ui.BillView
import dev.lkey.financility.feature_bill.presentation.current.viewmodel.BillAction
import dev.lkey.financility.feature_bill.presentation.current.viewmodel.BillEvent
import dev.lkey.financility.feature_bill.presentation.edit.viewmodel.EditBillAction
import dev.lkey.financility.feature_bill.presentation.edit.viewmodel.EditBillEvent
import dev.lkey.financility.feature_bill.presentation.edit.viewmodel.EditBillViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun EditBillScreen (
    navController: NavController,
    viewModel: EditBillViewModel = koinViewModel<EditBillViewModel>()
) {
    val state by viewModel.state.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.onEvent(EditBillEvent.OnLoadBill)

        viewModel.action.collectLatest { action ->
            when (action) {
                is EditBillAction.ShowSnackBar -> {
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
                title = stringResource(R.string.my_bill),
                actions = {
                    IconButton(
                        onClick = { /* TODO */ }
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
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->

        if (state.status != FinancilityResult.Success) {
            FinancilityLoadingBar(
                modifier = Modifier
                    .padding(padding)
            )
        } else {
            EditBillView(
                modifier = Modifier.padding(padding),
                state = state
            ) {
                viewModel.onEvent(it)
            }
        }

    }
}
