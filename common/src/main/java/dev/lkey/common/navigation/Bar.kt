package dev.lkey.common.navigation

import androidx.annotation.DrawableRes
import dev.lkey.common.R

/**
 Все табы приложения
 */
sealed class Bar (
    val route: Route,
    val title: Int,
    @DrawableRes val icon: Int
) {

    data object Expenses : Bar(
        route = Route.Expense,
        title = R.string.expenses_bar,
        icon = R.drawable.ic_expenses
    )

    data object Income : Bar(
        route = Route.Income,
        title = R.string.income_bar,
        icon = R.drawable.ic_income
    )

    data object Bill : Bar(
        route = Route.Bill,
        title = R.string.bill_bar,
        icon = R.drawable.ic_bill
    )

    data object Articles : Bar(
        route = Route.Articles,
        title = R.string.articles_bar,
        icon = R.drawable.ic_articles
    )

    data object Settings : Bar(
        route = Route.Settings,
        title = R.string.settings_bar,
        icon = R.drawable.ic_settings
    )

    companion object {
        val items = listOf(Expenses, Income, Bill, Articles, Settings)
    }
}