package dev.lkey.financility.core.app

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import dev.lkey.financility.navigation.splash.SplashScreen
import dev.lkey.financility.navigation.util.Route
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
                composable<Route.TodayExpenses> {
//                    ExpensesScreen(
//                        navController = navController
//                    )
                }

                composable<Route.HistoryExpenses> {
//                    HistoryExpensesScreen(
//                        navController = navController
//                    )
                }

                composable<Route.CreateExpenses> {
//                    CreateExpensesScreen(
//                        navController = navController
//                    )
                }
            }

            navigation<Route.Income>(
                startDestination = Route.TodayIncome
            ) {
                composable<Route.TodayIncome> {
//                    IncomeScreen(
//                        navController = navController
//                    )
                }

                composable<Route.HistoryIncomes> {
//                    HistoryIncomeScreen(
//                        navController = navController
//                    )
                }
            }

            navigation<Route.Bill>(
                startDestination = Route.CurrentBill
            ) {
                composable<Route.CurrentBill> {
//                    BillScreen(
//                        navController = navController
//                    )
                }

                composable<Route.EditBill> {
//                    EditBillScreen(
//                        navController = navController
//                    )
                }


            }

            navigation<Route.Articles>(
                startDestination = Route.MyArticles
            ) {
                composable<Route.MyArticles> {
//                    ArticlesScreen(
//                        navController = navController
//                    )
                }
            }

            navigation<Route.Settings>(
                startDestination = Route.AllSettings
            ) {
                composable<Route.AllSettings> {
//                    SettingsScreen(
//                        navController = navController
//                    )
                }
            }
        }
    }
}
