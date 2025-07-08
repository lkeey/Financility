package dev.lkey.common.ui.field

import android.app.TimePickerDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import dev.lkey.common.ui.item.FinancilityListItem
import java.util.Calendar

@Composable
fun FinancilityTimePicker (
    title: String,
    previousValue: String,
    backgroundColor: Color = MaterialTheme.colorScheme.onSurface,
    onChangeTime: (String) -> Unit
) {
    var selectedTime by remember {
        mutableStateOf(previousValue)
    }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val data = previousValue.split(":")
    val hour = if (data.size > 1) data[0].toIntOrNull() ?: calendar.get(Calendar.HOUR_OF_DAY) else calendar.get(Calendar.HOUR_OF_DAY)
    val minute = if (data.size > 1) data[1].toIntOrNull() ?: calendar.get(Calendar.MINUTE) else calendar.get(Calendar.MINUTE)

    val timePickerDialog = remember {
        TimePickerDialog(
            context,
            { _, selectedHour: Int, selectedMinute: Int ->
                val formatted = "%02d:%02d".format(selectedHour, selectedMinute)
                selectedTime = formatted
                onChangeTime(formatted)
            },
            hour,
            minute,
            true
        )
    }

    FinancilityListItem(
        title = title,
        trailingText = selectedTime,
        backgroundColor = backgroundColor,
        isClickable = true
    ) {
        timePickerDialog.show()
    }
}
