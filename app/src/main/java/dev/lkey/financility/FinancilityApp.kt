package dev.lkey.financility

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import dev.lkey.financility.feature_articles.ArticlesScreen
import dev.lkey.financility.feature_bill.BillScreen
import dev.lkey.financility.feature_expenses.ExpensesScreen
import dev.lkey.financility.feature_income.IncomeScreen
import dev.lkey.financility.feature_settings.SettingsScreen
import dev.lkey.financility.navigation.Bar
import dev.lkey.financility.navigation.Route
import dev.lkey.financility.navigation.splash.SplashScreen
import dev.lkey.financility.ui.theme.FinancilityTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinancilityApp() {
    FinancilityTheme {

        val navController = rememberNavController()
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

        val isSplash = Route.Splash.toString() in currentRoute.toString()

        Scaffold (
            bottomBar = {
                if (!isSplash) {
                    BottomAppBar(
                        containerColor = MaterialTheme.colorScheme.surface
                    ) {
                        for (bar in Bar.items) {
                            val isSelected = bar.route.toString() in currentRoute.toString()
                            NavigationBarItem(
                                selected = isSelected,
                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = MaterialTheme.colorScheme.outline
                                ),
                                onClick = {
                                    navController.navigate(bar.route)
                                },
                                icon = {
                                    Icon(
                                        painter = painterResource(id = bar.icon),
                                        contentDescription = bar.title,
                                        tint = if (isSelected) MaterialTheme.colorScheme.surfaceTint
                                            else MaterialTheme.colorScheme.inverseOnSurface
                                    )
                                },
                                label = {
                                    Text(
                                        text = bar.title,
                                        color = MaterialTheme.colorScheme.inverseOnSurface,
                                    )
                                }
                            )
                        }
                    }
                }
            },
            topBar = {
                if (!isSplash) {

                    TopAppBar(
                        title = {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = "Top Bar",
                                color = MaterialTheme.colorScheme.inverseOnSurface,
                                textAlign = TextAlign.Center
                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            },
            modifier = Modifier
                .fillMaxSize(),
            containerColor =
                    if (isSplash) MaterialTheme.colorScheme.onSurfaceVariant
                    else MaterialTheme.colorScheme.onSurface,

        ) { padding ->
            NavHost(
                navController = navController,
                startDestination = Route.Splash,
                modifier = Modifier
                    .padding(padding)
            ) {
                composable<Route.Splash>(
                    exitTransition = { slideOutHorizontally() },
                    popEnterTransition = { slideInHorizontally() }
                ) {
                    SplashScreen(
                        navController = navController
                    )
                }

                navigation<Route.Expenses>(
                    startDestination = Route.TodayExpenses
                ) {
                    composable<Route.TodayExpenses>(
                        exitTransition = { slideOutHorizontally() },
                        popEnterTransition = { slideInHorizontally() }
                    ) {
                        ExpensesScreen(
                            navController = navController
                        )
                    }
                }

                navigation<Route.Income>(
                    startDestination = Route.TodayIncome
                ) {
                    composable<Route.TodayIncome>(
                        exitTransition = { slideOutHorizontally() },
                        popEnterTransition = { slideInHorizontally() }
                    ) {
                        IncomeScreen(
                            navController = navController
                        )
                    }
                }

                navigation<Route.Bill>(
                    startDestination = Route.CurrentBill
                ) {
                    composable<Route.CurrentBill>(
                        exitTransition = { slideOutHorizontally() },
                        popEnterTransition = { slideInHorizontally() }
                    ) {
                        BillScreen(
                            navController = navController
                        )
                    }
                }

                navigation<Route.Articles>(
                    startDestination = Route.MyArticles
                ) {
                    composable<Route.MyArticles>(
                        exitTransition = { slideOutHorizontally() },
                        popEnterTransition = { slideInHorizontally() }
                    ) {
                        ArticlesScreen(
                            navController = navController
                        )
                    }
                }

                navigation<Route.Settings>(
                    startDestination = Route.AllSettings
                ) {
                    composable<Route.AllSettings>(
                        exitTransition = { slideOutHorizontally() },
                        popEnterTransition = { slideInHorizontally() }
                    ) {
                        SettingsScreen(
                            navController = navController
                        )
                    }
                }
            }
        }
    }

}
