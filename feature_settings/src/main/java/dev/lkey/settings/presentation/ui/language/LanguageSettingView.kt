package dev.lkey.settings.presentation.ui.language

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.lkey.settings.presentation.viewmodel.SettingsEvent
import dev.lkey.settings.presentation.viewmodel.SettingsState

@Composable
fun LanguageSettingView (
    modifier: Modifier = Modifier,
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Text(text = stringResource(dev.lkey.settings.R.string.choose_lang))

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            val buttonColors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
                disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                disabledContentColor = Color.LightGray
            )

            Button(
                onClick = { onEvent(SettingsEvent.OnChooseLanguage("en")) },
                enabled = state.language != "en",
                colors = buttonColors
            ) {
                Text(text = stringResource(dev.lkey.settings.R.string.english))
            }

            Button(
                onClick = { onEvent(SettingsEvent.OnChooseLanguage("ru")) },
                enabled = state.language != "ru",
                colors = buttonColors
            ) {
                Text(text = stringResource(dev.lkey.settings.R.string.russian))
            }

        }
    }
}
