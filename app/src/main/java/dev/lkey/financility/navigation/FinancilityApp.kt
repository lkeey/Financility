package dev.lkey.financility.navigation

import android.net.Uri
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.lkey.articles.di.DaggerArticlesComponent
import dev.lkey.articles.presentation.ui.ArticlesScreen
import dev.lkey.bill.di.DaggerBillComponent
import dev.lkey.bill.presentation.current.ui.BillScreen
import dev.lkey.bill.presentation.edit.ui.EditBillScreen
import dev.lkey.common.core.model.TransactionModel
import dev.lkey.common.navigation.Route
import dev.lkey.common.theme.FinancilityTheme
import dev.lkey.common.theme.ThemeMode
import dev.lkey.core.di.utils.CoreProvider
import dev.lkey.feature_splash.di.DaggerSplashComponent
import dev.lkey.feature_splash.presentation.ui.SplashScreen
import dev.lkey.settings.di.DaggerSettingsComponent
import dev.lkey.settings.presentation.ui.code.CodeSettingsScreen
import dev.lkey.settings.presentation.ui.color.ColorSettingsScreen
import dev.lkey.settings.presentation.ui.common.SettingsScreen
import dev.lkey.settings.presentation.ui.language.LanguageSettingScreen
import dev.lkey.settings.presentation.ui.sync.SyncSettingsScreen
import dev.lkey.settings.presentation.ui.version.VersionSettingsScreen
import dev.lkey.storage.di.DaggerDatabaseComponent
import dev.lkey.transations.di.DaggerTransactionComponent
import dev.lkey.transations.presentation.analysis.ui.AnalysisScreen
import dev.lkey.transations.presentation.create.ui.CreateTransactionScreen
import dev.lkey.transations.presentation.detail.ui.UpdateTransactionScreen
import dev.lkey.transations.presentation.expenses.history.ui.HistoryExpensesScreen
import dev.lkey.transations.presentation.expenses.today.ui.ExpensesScreen
import dev.lkey.transations.presentation.income.history.ui.HistoryIncomeScreen
import dev.lkey.transations.presentation.income.today.ui.IncomeScreen
import kotlinx.serialization.json.Json

/**
 * - Навигарует на нужный Route
 * - Создает компоненты фичей для получения VM экрана
 */

@Composable
fun FinancilityApp(
    theme: ThemeMode,
    primaryColor: Color
) {

    val provider = LocalContext.current.applicationContext as CoreProvider
    val db = DaggerDatabaseComponent.factory().create(provider.coreComponent)

    val splashComponent = DaggerSplashComponent.factory().create(provider.coreComponent, db)
    val billComponent = DaggerBillComponent.factory().create(provider.coreComponent, db)
    val articlesComponent = DaggerArticlesComponent.factory().create(provider.coreComponent, db)
    val transactionsComponent = DaggerTransactionComponent.factory().create(provider.coreComponent, db)
    val settingsComponent = DaggerSettingsComponent.factory().create(provider.coreComponent, db)

    FinancilityTheme (
        themeMode = theme,
        primaryColor = primaryColor
    ) {
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
                    navController = navController,
                    viewModel = viewModel(factory = splashComponent.viewModelFactory()),
                )
            }

            navigation<Route.Expense>(
                startDestination = Route.TodayExpense
            ) {
                composable<Route.TodayExpense> {
                    ExpensesScreen(
                        navController = navController,
                        viewModel = viewModel(factory = transactionsComponent.viewModelFactory()),
                    )
                }

                composable<Route.HistoryExpense> {
                    HistoryExpensesScreen(
                        navController = navController,
                        viewModel = viewModel(factory = transactionsComponent.viewModelFactory()),
                    )
                }

                composable<Route.CreateExpense> {
                    CreateTransactionScreen(
                        navController = navController,
                        viewModel = viewModel(factory = transactionsComponent.viewModelFactory()),
                        isIncome = false
                    )
                }

                composable(
                    route = "${Route.UpdateExpense}/{transaction}",
                    arguments = listOf(navArgument("transaction") { type = NavType.StringType })
                ) { backStackEntry ->

                    val json = backStackEntry.arguments?.getString("transaction")
                    val transaction = json?.let {
                        Json.decodeFromString<TransactionModel>(Uri.decode(it))
                    }

                    if (transaction != null) {
                        UpdateTransactionScreen(
                            navController = navController,
                            viewModel = viewModel(factory = transactionsComponent.viewModelFactory()),
                            transaction = transaction,
                            isIncome = false
                        )
                    }
                }

                composable<Route.AnalysisExpense>{ backStackEntry ->
                    AnalysisScreen(
                        navController = navController,
                        viewModel = viewModel(factory = transactionsComponent.viewModelFactory()),
                        isIncome = false
                    )
                }
            }

            navigation<Route.Income>(
                startDestination = Route.TodayIncome
            ) {
                composable<Route.TodayIncome> {
                    IncomeScreen(
                        navController = navController,
                        viewModel = viewModel(factory = transactionsComponent.viewModelFactory()),
                    )
                }

                composable<Route.HistoryIncome> {
                    HistoryIncomeScreen(
                        navController = navController,
                        viewModel = viewModel(factory = transactionsComponent.viewModelFactory()),
                    )
                }

                composable<Route.CreateIncome> {
                    CreateTransactionScreen(
                        navController = navController,
                        viewModel = viewModel(factory = transactionsComponent.viewModelFactory()),
                        isIncome = true
                    )
                }

                composable(
                    route = "${Route.UpdateIncome}/{transaction}",
                    arguments = listOf(navArgument("transaction") { type = NavType.StringType })
                ) { backStackEntry ->

                    val json = backStackEntry.arguments?.getString("transaction")
                    val transaction = json?.let {
                        Json.decodeFromString<TransactionModel>(Uri.decode(it))
                    }

                    if (transaction != null) {
                        UpdateTransactionScreen(
                            navController = navController,
                            viewModel = viewModel(factory = transactionsComponent.viewModelFactory()),
                            transaction = transaction,
                            isIncome = true
                        )
                    }
                }

                composable<Route.AnalysisIncome>{ backStackEntry ->
                    AnalysisScreen(
                        navController = navController,
                        viewModel = viewModel(factory = transactionsComponent.viewModelFactory()),
                        isIncome = true
                    )
                }

            }

            navigation<Route.Bill>(
                startDestination = Route.CurrentBill
            ) {
                composable<Route.CurrentBill> {
                    BillScreen(
                        navController = navController,
                        viewModel = viewModel(factory = billComponent.viewModelFactory()),
                    )
                }

                composable<Route.EditBill> {
                    EditBillScreen(
                        navController = navController,
                        viewModel = viewModel(factory = billComponent.viewModelFactory()),
                    )
                }
            }

            navigation<Route.Articles>(
                startDestination = Route.MyArticles
            ) {

                composable<Route.MyArticles> {
                    ArticlesScreen(
                        viewModel = viewModel(factory = articlesComponent.viewModelFactory()),
                        navController = navController
                    )
                }
            }

            navigation<Route.Settings>(
                startDestination = Route.AllSettings
            ) {
                composable<Route.AllSettings> {
                    SettingsScreen(
                        navController = navController,
                        viewModel = viewModel(factory = settingsComponent.viewModelFactory())
                    )
                }

                composable<Route.CodeSettings> {
                    CodeSettingsScreen(
                        navController = navController,
                        viewModel = viewModel(factory = settingsComponent.viewModelFactory())
                    )
                }

                composable<Route.SyncSettings> {
                    SyncSettingsScreen(
                        navController = navController,
                        viewModel = viewModel(factory = settingsComponent.viewModelFactory())
                    )
                }

                composable<Route.LanguageSettings> {
                    LanguageSettingScreen(
                        navController = navController,
                        viewModel = viewModel(factory = settingsComponent.viewModelFactory())
                    )
                }

                composable<Route.ColorSettings> {
                    ColorSettingsScreen(
                        navController = navController,
                        viewModel = viewModel(factory = settingsComponent.viewModelFactory())
                    )
                }

                composable<Route.VersionSettings> {
                    VersionSettingsScreen(
                        navController = navController,
                        viewModel = viewModel(factory = settingsComponent.viewModelFactory())
                    )
                }
            }
        }
    }
}
