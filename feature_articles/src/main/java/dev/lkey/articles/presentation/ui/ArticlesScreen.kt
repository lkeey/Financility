package dev.lkey.articles.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkey.articles.presentation.viewmodel.ArticleAction
import dev.lkey.articles.presentation.viewmodel.ArticlesEvent
import dev.lkey.articles.presentation.viewmodel.ArticlesViewModel
import dev.lkey.common.ui.item.FinancilityErrorMessage
import dev.lkey.common.ui.item.FinancilityLoadingBar
import dev.lkey.common.ui.item.FinancilitySnackBar
import dev.lkey.common.ui.nav.FinancilityBottomBar
import dev.lkey.common.ui.nav.FinancilityTopBar
import dev.lkey.core.network.FinancilityResult
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ArticlesScreen (
    viewModel: ArticlesViewModel,
    navController: NavController
) {

    var error: String? by remember {
        mutableStateOf(null)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.onEvent(ArticlesEvent.OnLoadArticles)

        viewModel.action.collectLatest { action ->
            when (action) {
                is ArticleAction.ShowSnackBar -> {
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
                title = "Мои статьи",
                actions = { }
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
                        viewModel.onEvent(ArticlesEvent.OnLoadArticles)
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
                ArticlesView(
                    modifier = Modifier.padding(padding),
                    state = state,
                    onEvent = {
                        viewModel.onEvent(it)
                    }
                )
            }
        }
    }
}
