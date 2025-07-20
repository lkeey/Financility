package dev.lkey.common.navigation

import kotlinx.serialization.Serializable

/**
 * Интерфейс для всех вложенных навигаций
 * */

sealed interface Route {

    @Serializable
    data object Splash : Route

    @Serializable
    data object Expense : Route

    @Serializable
    data object TodayExpense : Route

    @Serializable
    data object CreateExpense : Route

    @Serializable
    data object HistoryExpense : Route

    @Serializable
    data object UpdateExpense : Route

    @Serializable
    data object AnalysisExpense : Route

    @Serializable
    data object Income : Route

    @Serializable
    data object HistoryIncome : Route

    @Serializable
    data object TodayIncome : Route

    @Serializable
    data object CreateIncome : Route

    @Serializable
    data object UpdateIncome : Route

    @Serializable
    data object AnalysisIncome : Route

    @Serializable
    data object Bill : Route

    @Serializable
    data object CurrentBill : Route

    @Serializable
    data object EditBill : Route

    @Serializable
    data object Articles : Route

    @Serializable
    data object MyArticles : Route

    @Serializable
    data object Settings : Route

    @Serializable
    data object AllSettings : Route

    @Serializable
    data object SyncSettings : Route

}