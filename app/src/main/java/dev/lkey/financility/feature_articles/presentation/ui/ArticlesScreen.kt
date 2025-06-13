package dev.lkey.financility.feature_articles.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.lkey.financility.components.FinancilityBottomBar
import dev.lkey.financility.components.FinancilityTopBar
import dev.lkey.financility.feature_articles.presentation.ArticlesViewModel
import dev.lkey.financility.feature_expenses.presentation.ExpensesViewModel

@Composable
fun ArticlesScreen (
    viewModel: ArticlesViewModel = ArticlesViewModel(),
    navController: NavController
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold (
        bottomBar = {
            FinancilityBottomBar(
                navController = navController
            )
        },
        topBar = {
            FinancilityTopBar(
                title = "Мои статьи",
                icon = { }
            )
        },
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.onSurface,
    ) { padding ->

        ArticlesView(
            modifier = Modifier.padding(padding),
            state = state,
            onEvent = {
                viewModel.onEvent(it)
            }
        )

    }
}
