package dev.lkey.financility.components

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinancilityDayPicker(
    onChangeDate: (String) -> Unit
) {
    var showDialog by remember { mutableStateOf(true) }
    val selectedDateLabel = remember { mutableStateOf("") }
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Picker)

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
                        datePickerState.selectedDateMillis?.convertMillisToDate() ?: ""

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
}

fun Long.convertMillisToDate(): String {
    val calendar = Calendar.getInstance().apply {
        timeInMillis = this@convertMillisToDate
        val zoneOffset = get(Calendar.ZONE_OFFSET)
        val dstOffset = get(Calendar.DST_OFFSET)
        add(Calendar.MILLISECOND, -(zoneOffset + dstOffset))
    }
    val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.US)
    return sdf.format(calendar.time)
}