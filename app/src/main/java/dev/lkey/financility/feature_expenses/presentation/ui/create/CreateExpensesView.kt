package dev.lkey.financility.feature_expenses.presentation.ui.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lkey.financility.R
import dev.lkey.financility.components.FinancilityButton
import dev.lkey.financility.components.FinancilityDayPicker
import dev.lkey.financility.components.FinancilityDropDown
import dev.lkey.financility.components.FinancilityEditText
import dev.lkey.financility.components.FinancilityListItem
import dev.lkey.financility.components.FinancilityNumTextField
import dev.lkey.financility.components.FinancilityTimePicker
import dev.lkey.financility.feature_expenses.presentation.ExpensesEvent
import dev.lkey.financility.feature_expenses.presentation.ExpensesState

@Composable
fun CreateExpensesView (
    modifier: Modifier = Modifier,
    state: ExpensesState,
    onEvent: (ExpensesEvent) -> Unit
) {
    Column (
        modifier = modifier
            .fillMaxSize()
    ){
        if (state.accounts.isNotEmpty()) {
            FinancilityListItem (
                title = "Счет",
                trailingText = state.accounts[0].name,
                isClickable = false,
                trailingIcon = R.drawable.ic_light_arrow
            )
        }

        FinancilityDropDown(
            title = "Статья",
            options = listOf("Еда", "Транспорт", "Развлечения", "Здоровье"),
            previousData = "Еда",
            onOptionSelected = {},
        )

        FinancilityNumTextField (
            title = "Сумма",
            previousData = "0",
        ) {

        }

        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceDim,
        )

        FinancilityDayPicker (
            title = "Дата",
            previousValue = ""
        ) {

        }

        FinancilityTimePicker(
            title = "Выбери время",
            previousValue = "12:00"
        ) {

        }

        FinancilityEditText(
            previousData = "",
            label = "Комментарий",
            isShowTrailingIcon = false,
            backgroundColor = MaterialTheme.colorScheme.onSurface
        ) {

        }

        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceDim,
        )


        FinancilityButton(
            text = "Удалить расход",
            onClick = {}
        )
    }
}