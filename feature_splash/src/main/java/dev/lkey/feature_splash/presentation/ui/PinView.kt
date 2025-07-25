package dev.lkey.feature_splash.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import dev.lkey.feature_splash.presentation.viewmodel.SplashEvent
import dev.lkey.feature_splash.presentation.viewmodel.SplashState

@Composable
fun PinView (
    modifier: Modifier = Modifier,
    state: SplashState,
    onEvent: (SplashEvent) -> Unit
) {
    Column(
        modifier = modifier.padding(32.dp)
    ) {
        Text("Введите PIN")

        OutlinedTextField(
            value = state.pin,
            onValueChange = {
                if (it.all { ch -> ch.isDigit() }) {
                    onEvent(SplashEvent.OnEnterPin(it))
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
