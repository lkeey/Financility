package dev.lkey.common.ui.field

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
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
import androidx.compose.ui.window.DialogProperties
import dev.lkey.common.core.converter.convertDateToMillis
import dev.lkey.common.core.converter.convertMillisToDate


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinancilityDayPicker(
    title: String,
    previousValue: String,
    isChip: Boolean = false,
    backgroundColor: Color = MaterialTheme.colorScheme.onSurface,
    onChangeDate: (String) -> Unit
) {
    
    var showDialog by remember { mutableStateOf(false) }
    val selectedDateLabel = remember { mutableStateOf(previousValue) }
    val datePickerState = rememberDatePickerState(
        initialDisplayMode = DisplayMode.Picker,
        initialSelectedDateMillis = convertDateToMillis(previousValue)
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
                .clickable {
                    showDialog = true
                }
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

            if (isChip) {
                SuggestionChip(
                    onClick = {
                        showDialog = true
                    },
                    label = {
                        Text(
                            text = selectedDateLabel.value,
                            color = MaterialTheme.colorScheme.inverseOnSurface,
                            fontWeight = FontWeight.W500,
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            letterSpacing = 0.5.sp,
                        )
                    },
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                    ),
                    shape = RoundedCornerShape(100.dp)
                )
            } else {
                Text(
                    text = selectedDateLabel.value,
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.5.sp,
                )

                Spacer(modifier = Modifier.width(12.dp))

            }
        }
    }
}

