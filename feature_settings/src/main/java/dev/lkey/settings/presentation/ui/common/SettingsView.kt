package dev.lkey.settings.presentation.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lkey.common.R
import dev.lkey.common.navigation.Route
import dev.lkey.common.ui.field.FinancilityToggleListItem
import dev.lkey.common.ui.item.FinancilityListItem

@Composable
fun SettingsView (
    modifier: Modifier = Modifier,
    onNavigate: (Route) -> Unit
) {

    val options = listOf(
        "Основной цвет",
        "Звуки",
        "Хаптики",
        "Код пароль",

        "Синхронизация",

        "Язык",
        "О программе"
    )

    val routes = listOf(
        Route.AllSettings,
        Route.AllSettings,
        Route.AllSettings,
        Route.AllSettings,

        Route.SyncSettings,

        Route.AllSettings,
        Route.AllSettings,
    )

    Column (
        modifier = modifier
            .fillMaxSize()
    ){
        FinancilityToggleListItem(
            title = "Светлая темная авто",
            isChecked = false,
            onClick = {
                /* TODO */
            }
        )

        options.forEachIndexed { num, option ->
            FinancilityListItem(
                trailingIcon = R.drawable.ic_dark_arrow,
                title = option,
                height = 56.dp
            ) {
                onNavigate(routes[num])
            }
        }
    }

}
