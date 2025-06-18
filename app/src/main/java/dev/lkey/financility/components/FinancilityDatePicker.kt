package dev.lkey.financility.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinancilityDayPicker(
    title: String,
    previousValue: String,
    backgroundColor: Color = MaterialTheme.colorScheme.onSurface,
    onChangeDate: (String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    val selectedDateLabel = remember { mutableStateOf(previousValue) }
    val datePickerState = rememberDatePickerState(
        initialDisplayMode = DisplayMode.Picker,
        initialSelectedDateMillis = Calendar.getInstance().timeInMillis
    )

    if (showDialog) {
        DatePickerDialog(
            modifier = Modifier
                .padding(horizontal = 20.dp),
            onDismissRequest = {
                showDialog = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("OK")

                    selectedDateLabel.value =
                        convertMillisToDate(datePickerState.selectedDateMillis ?: selectedDateLabel.value.toLong())

                    onChangeDate(selectedDateLabel.value)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Отмена")
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    selectedDayContainerColor = MaterialTheme.colorScheme.primary,
                    selectedYearContainerColor = MaterialTheme.colorScheme.primary,
                    todayDateBorderColor = MaterialTheme.colorScheme.primary,

                    containerColor = MaterialTheme.colorScheme.surfaceContainerLow,

                    todayContentColor = MaterialTheme.colorScheme.inverseSurface,
                    dayContentColor = MaterialTheme.colorScheme.inverseSurface,
                    selectedYearContentColor = MaterialTheme.colorScheme.inverseSurface,
                    selectedDayContentColor = MaterialTheme.colorScheme.inverseSurface,
                    yearContentColor = MaterialTheme.colorScheme.inverseSurface,
                    weekdayContentColor = MaterialTheme.colorScheme.inverseSurface,
                    titleContentColor = MaterialTheme.colorScheme.inverseSurface,
                    headlineContentColor = MaterialTheme.colorScheme.inverseSurface,
                    subheadContentColor = MaterialTheme.colorScheme.inverseSurface,
                    dayInSelectionRangeContentColor = MaterialTheme.colorScheme.inverseSurface,
                )
            )
        }
    }

    FinancilityListItem(
        title = title,
        description = null,
        backgroundColor = backgroundColor,
        trailingText = selectedDateLabel.value,
        isClickable = true,
    ) {
        showDialog = true
    }
}

fun convertMillisToDate(
    millis : Long
): String {
    val localDate = Instant.ofEpochMilli(millis)
        .atZone(ZoneId.systemDefault()) // Использует часовой пояс устройства
        .toLocalDate()

    return localDate.format(DateTimeFormatter.ISO_DATE)
}