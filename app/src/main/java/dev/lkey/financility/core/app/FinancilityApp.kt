package dev.lkey.financility.core.app

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import dev.lkey.articles.presentation.ui.ArticlesScreen
import dev.lkey.bill.presentation.current.ui.BillScreen
import dev.lkey.bill.presentation.edit.ui.EditBillScreen
import dev.lkey.common.navigation.Route
import dev.lkey.financility.navigation.splash.SplashScreen
import dev.lkey.financility.ui.theme.FinancilityTheme
import dev.lkey.settings.SettingsScreen
import dev.lkey.transations.presentation.expenses.create.ui.CreateExpensesScreen
import dev.lkey.transations.presentation.expenses.history.ui.HistoryExpensesScreen
import dev.lkey.transations.presentation.expenses.today.ui.ExpensesScreen
import dev.lkey.transations.presentation.income.history.ui.HistoryIncomeScreen
import dev.lkey.transations.presentation.income.today.ui.IncomeScreen

@Composable
fun FinancilityApp(
    viewModelFactory: ViewModelProvider.Factory
) {
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
                    ExpensesScreen(
                        navController = navController,
                        viewModel = viewModel(factory = viewModelFactory),
                    )
                }

                composable<Route.HistoryExpenses> {
                    HistoryExpensesScreen(
                        navController = navController,
                        viewModel = viewModel(factory = viewModelFactory),
                    )
                }

                composable<Route.CreateExpenses> {
                    CreateExpensesScreen(
                        navController = navController,
                        viewModel = viewModel(factory = viewModelFactory),
                    )
                }
            }

            navigation<Route.Income>(
                startDestination = Route.TodayIncome
            ) {
                composable<Route.TodayIncome> {
                    IncomeScreen(
                        navController = navController,
                        viewModel = viewModel(factory = viewModelFactory),
                    )
                }

                composable<Route.HistoryIncomes> {
                    HistoryIncomeScreen(
                        navController = navController,
                        viewModel = viewModel(factory = viewModelFactory),
                    )
                }
            }

            navigation<Route.Bill>(
                startDestination = Route.CurrentBill
            ) {
                composable<Route.CurrentBill> {
                    BillScreen(
                        navController = navController,
                        viewModel = viewModel(factory = viewModelFactory),
                    )
                }

                composable<Route.EditBill> {
                    EditBillScreen(
                        navController = navController,
                        viewModel = viewModel(factory = viewModelFactory),
                    )
                }
            }

            navigation<Route.Articles>(
                startDestination = Route.MyArticles
            ) {
                composable<Route.MyArticles> {
                    ArticlesScreen(
                        viewModel = viewModel(factory = viewModelFactory),
                        navController = navController
                    )
                }
            }

            navigation<Route.Settings>(
                startDestination = Route.AllSettings
            ) {
                composable<Route.AllSettings> {
                    SettingsScreen(
                        navController = navController
                    )
                }
            }
        }
    }
}
