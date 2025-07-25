package dev.lkey.settings.presentation.ui.code

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import dev.lkey.settings.presentation.viewmodel.SettingsEvent
import dev.lkey.settings.presentation.viewmodel.SettingsState

@Composable
fun CodeSettingsView (
    modifier: Modifier = Modifier,
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(if (state.stage == 0) "Введите PIN" else "Повторите PIN")

            OutlinedTextField(
                modifier = Modifier
                    .testTag("PinInput"),
                value = if (state.stage == 0) state.firstEntry else state.confirmEntry,
                onValueChange = {
                    if (it.length <= 4 && it.all { ch -> ch.isDigit() }) {
                        onEvent(SettingsEvent.OnEnterPin(it))
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.surfaceContainer,
                    unfocusedTextColor = MaterialTheme.colorScheme.surfaceContainer,
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true
            )
        }
    }
}
