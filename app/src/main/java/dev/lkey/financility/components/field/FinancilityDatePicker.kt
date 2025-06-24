package dev.lkey.financility.components.field

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import dev.lkey.financility.components.item.FinancilityListItem
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
    
//    TODO ставить выбранную дату
    
    var showDialog by remember { mutableStateOf(false) }
    val selectedDateLabel = remember { mutableStateOf(previousValue) }
    val datePickerState = rememberDatePickerState(
        initialDisplayMode = DisplayMode.Picker,
        initialSelectedDateMillis = Calendar.getInstance().timeInMillis
    )

    val pickerColors = DatePickerDefaults.colors(
        selectedDayContainerColor = MaterialTheme.colorScheme.primary,
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,

        titleContentColor = MaterialTheme.colorScheme.inverseOnSurface,
        headlineContentColor = MaterialTheme.colorScheme.inverseOnSurface,
        weekdayContentColor = MaterialTheme.colorScheme.inverseOnSurface,
        subheadContentColor = MaterialTheme.colorScheme.inverseOnSurface,
        yearContentColor = MaterialTheme.colorScheme.surfaceContainer,
        disabledYearContentColor = MaterialTheme.colorScheme.surfaceContainer,
        selectedYearContentColor = MaterialTheme.colorScheme.inverseOnSurface,
        selectedYearContainerColor = MaterialTheme.colorScheme.primary,
        disabledSelectedYearContainerColor = MaterialTheme.colorScheme.primary,
        currentYearContentColor = MaterialTheme.colorScheme.inverseOnSurface,
        dayContentColor = MaterialTheme.colorScheme.inverseOnSurface,
        disabledDayContentColor = MaterialTheme.colorScheme.surfaceContainer,
        selectedDayContentColor = MaterialTheme.colorScheme.inverseOnSurface,

        todayContentColor = MaterialTheme.colorScheme.inverseOnSurface,
        todayDateBorderColor = Color.Transparent,
        dayInSelectionRangeContainerColor = MaterialTheme.colorScheme.inverseOnSurface,
        dayInSelectionRangeContentColor = MaterialTheme.colorScheme.inverseOnSurface,
        dividerColor = MaterialTheme.colorScheme.inverseOnSurface,
        navigationContentColor = MaterialTheme.colorScheme.surfaceContainer,
        dateTextFieldColors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.inverseOnSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.inverseOnSurface,
            disabledTextColor = MaterialTheme.colorScheme.surfaceContainer,
            errorTextColor = MaterialTheme.colorScheme.tertiaryContainer,

            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            errorContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,

            cursorColor = MaterialTheme.colorScheme.primary,
            errorCursorColor = MaterialTheme.colorScheme.tertiaryContainer,

            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.surfaceContainer,
            disabledIndicatorColor = MaterialTheme.colorScheme.surfaceContainer,
            errorIndicatorColor = MaterialTheme.colorScheme.tertiaryContainer,

            selectionColors = TextSelectionColors(
                handleColor = MaterialTheme.colorScheme.primary,
                backgroundColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
            )
        )
    )


    if (showDialog) {
        DatePickerDialog(
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                decorFitsSystemWindows = false
            ),
            shape = RoundedCornerShape(15.dp),
            tonalElevation = 0.dp,
            onDismissRequest = {
                showDialog = false
            },
            colors = pickerColors,
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false

                        onChangeDate(selectedDateLabel.value)
                    }
                ) {
                    Text("OK")

                    selectedDateLabel.value =
                        convertMillisToDate(datePickerState.selectedDateMillis ?: selectedDateLabel.value.toLong())
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                colors = pickerColors,
                title = null,
                headline = null,
                showModeToggle = false
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