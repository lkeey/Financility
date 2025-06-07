package dev.lkey.financility.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Expenses : Route

    @Serializable
    data object TodayExpenses : Route

    @Serializable
    data object Income : Route

    @Serializable
    data object TodayIncome : Route

    @Serializable
    data object Bill : Route

    @Serializable
    data object CurrentBill : Route

    @Serializable
    data object Articles : Route

    @Serializable
    data object MyArticles : Route

    @Serializable
    data object Settings : Route

    @Serializable
    data object AllSettings : Route

}