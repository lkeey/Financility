package dev.lkey.common.ui.field

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FinancilityToggleListItem (
    title: String,
    isChecked : Boolean = false,
    backgroundColor: Color = MaterialTheme.colorScheme.onSurface,
    onClick: (Boolean) -> Unit
) {

    var checked by remember { mutableStateOf(isChecked) }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor),
        verticalArrangement = Arrangement.Center
    ) {
        Row (
            modifier = Modifier
                .height(56.dp)
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f),
                text = title,
                fontWeight = FontWeight.W400,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp,
                color = MaterialTheme.colorScheme.inverseOnSurface
            )

            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it
                    onClick(it)
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.primary,
                    checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                    checkedBorderColor = MaterialTheme.colorScheme.primary,
                    uncheckedThumbColor = MaterialTheme.colorScheme.secondaryContainer,
                    uncheckedTrackColor = MaterialTheme.colorScheme.onSecondary,
                    uncheckedBorderColor = MaterialTheme.colorScheme.secondaryContainer
                )
            )
        }

        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceDim,
        )
    }
}
