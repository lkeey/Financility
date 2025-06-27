package dev.lkey.financility.feature_articles.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkey.financility.R
import dev.lkey.financility.components.nav.FinancilityBottomBar
import dev.lkey.financility.components.item.FinancilityLoadingBar
import dev.lkey.financility.components.item.FinancilitySnackBar
import dev.lkey.financility.components.nav.FinancilityTopBar
import dev.lkey.financility.core.network.FinancilityResult
import dev.lkey.financility.feature_articles.presentation.viewmodel.ArticleAction
import dev.lkey.financility.feature_articles.presentation.viewmodel.ArticlesEvent
import dev.lkey.financility.feature_articles.presentation.viewmodel.ArticlesViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArticlesScreen (
    viewModel: ArticlesViewModel = koinViewModel<ArticlesViewModel>(),
    navController: NavController
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.onEvent(ArticlesEvent.OnLoadArticles)

        viewModel.action.collectLatest { event ->
            when (event) {
                is ArticleAction.ShowSnackBar -> {
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
                title = stringResource(R.string.my_articles),
                actions = { }
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
