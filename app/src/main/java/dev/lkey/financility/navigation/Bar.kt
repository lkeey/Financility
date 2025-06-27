package dev.lkey.financility.navigation

import androidx.annotation.DrawableRes
import dev.lkey.financility.R

/**
 Все табы приложения
 */
sealed class Bar (
    val route: Route,
    val title: String,
    @DrawableRes val icon: Int
) {
    data object Expenses : Bar(
        route = Route.Expenses,
        title = "Расходы",
        icon = R.drawable.ic_income
    )

    data object Income : Bar(
        route = Route.Income,
        title = "Доходы",
        icon = R.drawable.ic_expenses
    )

    data object Bill : Bar(
        route = Route.Bill,
        title = "Счет",
        icon = R.drawable.ic_bill
    )

    data object Articles : Bar(
        route = Route.Articles,
        title = "Статьи",
        icon = R.drawable.ic_articles
    )

    data object Settings : Bar(
        route = Route.Settings,
        title = "Настройки",
        icon = R.drawable.ic_settings
    )

    companion object {
        val items = listOf(Expenses, Income, Bill, Articles, Settings)
    }
}