package dev.lkey.financility.feature_settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lkey.financility.R
import dev.lkey.financility.components.FinancilityToggleListItem
import dev.lkey.financility.components.FinancilityListItem

@Composable
fun SettingsView (
    modifier: Modifier = Modifier
) {

    val options = listOf("Основной цвет", "Звуки", "Хаптики", "Код пароль", "Синхронизация", "Язык", "О программе")

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

        options.forEach {
            FinancilityListItem(
                trailingIcon = R.drawable.ic_dark_arrow,
                title = it,
                height = 56.dp
            )
        }
    }
}