package dev.lkey.financility

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import dev.lkey.financility.feature_articles.ArticlesScreen
import dev.lkey.financility.feature_bill.BillScreen
import dev.lkey.financility.feature_expenses.presentation.ui.ExpensesScreen
import dev.lkey.financility.feature_income.IncomeScreen
import dev.lkey.financility.feature_settings.SettingsScreen
import dev.lkey.financility.navigation.Route
import dev.lkey.financility.navigation.splash.SplashScreen
import dev.lkey.financility.ui.theme.FinancilityTheme

@Composable
fun FinancilityApp() {
    FinancilityTheme {

        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Route.Splash,
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
