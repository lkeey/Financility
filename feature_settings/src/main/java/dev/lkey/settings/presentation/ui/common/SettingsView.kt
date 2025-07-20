package dev.lkey.settings.presentation.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.lkey.common.R
import dev.lkey.common.navigation.Route
import dev.lkey.common.ui.field.FinancilityToggleListItem
import dev.lkey.common.ui.item.FinancilityListItem
import dev.lkey.settings.presentation.viewmodel.SettingsEvent
import dev.lkey.settings.presentation.viewmodel.SettingsEvent.OnUpdateTheme
import dev.lkey.settings.presentation.viewmodel.SettingsState

@Composable
fun SettingsView (
    modifier: Modifier = Modifier,
    onNavigate: (Route) -> Unit,
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit
) {

    val options = listOf(
        stringResource(dev.lkey.settings.R.string.main_color),
        stringResource(dev.lkey.settings.R.string.sound),
        stringResource(dev.lkey.settings.R.string.haptics),
        stringResource(dev.lkey.settings.R.string.code),

        stringResource(dev.lkey.settings.R.string.sync),
        stringResource(dev.lkey.settings.R.string.lang),

        stringResource(dev.lkey.settings.R.string.about)
    )

    val routes = listOf(
        Route.ColorSettings,

        Route.AllSettings,
        Route.AllSettings,
        Route.AllSettings,

        Route.SyncSettings,
        Route.LanguageSettings,

        Route.AllSettings,
    )

    Column (
        modifier = modifier
            .fillMaxSize()
    ){

        FinancilityToggleListItem(
            title = stringResource(dev.lkey.settings.R.string.theme_color),
            current = state.theme
        ) {
            onEvent(OnUpdateTheme(it))
        }

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
