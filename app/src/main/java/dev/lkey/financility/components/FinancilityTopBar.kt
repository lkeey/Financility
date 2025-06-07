package dev.lkey.financility.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinancilityTopBar (
    title: String,
    icon: @Composable () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = title,
                color = MaterialTheme.colorScheme.inverseOnSurface,
                textAlign = TextAlign.Center
            )

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        actions = {
            icon.invoke()
        },
    )
}