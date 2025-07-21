package dev.lkey.settings.presentation.ui.version

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.lkey.settings.R
import dev.lkey.settings.presentation.viewmodel.SettingsState

@Composable
fun VersionSettingsView (
    modifier: Modifier = Modifier,
    state: SettingsState,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(stringResource(R.string.info_about_app))

        Spacer(Modifier.height(16.dp))

        Text(stringResource(R.string.version, state.appInfo.version))
        Text(stringResource(R.string.last_update, state.appInfo.lastUpdate))
    }

}
