package dev.lkey.financility.feature_expenses

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.lkey.financility.R
import dev.lkey.financility.components.FinancilityBottomBar
import dev.lkey.financility.components.FinancilityTopBar
import dev.lkey.financility.navigation.Bar
import dev.lkey.financility.navigation.Route

@Composable
fun ExpensesScreen (
    navController: NavController
) {
    Scaffold (
        bottomBar = {
            FinancilityBottomBar(
                navController = navController
            )
        },
        topBar = {
            FinancilityTopBar(
                title = "Расходы сегодня",
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_history),
                        contentDescription = "История",
                        tint = MaterialTheme.colorScheme.surfaceContainer
                    )
                }
            )
        },
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.onSurface,
    ) { padding ->

        ExpensesView(
            modifier = Modifier.padding(padding)
        )

    }
}