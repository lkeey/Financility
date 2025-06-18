package dev.lkey.financility.components

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
import androidx.compose.ui.graphics.Color
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinancilityDayPicker(
    title: String,
    previousValue: String,
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
                    selectedDayContainerColor = Color(0xFF722276),
                    selectedDayContentColor = Color.White,
                    selectedYearContainerColor = Color(0xFF722276),
                    selectedYearContentColor = Color.White,
                    todayContentColor = Color(0xFF722276),
                    todayDateBorderColor = Color(0xFF722276)
                )
            )
        }
    }

    FinancilityListItem(
        title = title,
        description = null,
        backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
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