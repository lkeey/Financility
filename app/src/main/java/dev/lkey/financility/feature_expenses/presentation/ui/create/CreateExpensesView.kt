package dev.lkey.financility.feature_expenses.presentation.ui.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.lkey.financility.R
import dev.lkey.financility.components.FinancilityButton
import dev.lkey.financility.components.FinancilityDayPicker
import dev.lkey.financility.components.FinancilityDropDown
import dev.lkey.financility.components.FinancilityEditText
import dev.lkey.financility.components.FinancilityListItem
import dev.lkey.financility.components.FinancilityNumTextField
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

        FinancilityListItem (
            title = "Статья",
            trailingText = "...",
            isClickable = true,
            trailingIcon = R.drawable.ic_light_arrow
        ) {

        }

        FinancilityNumTextField (
            title = "Сумма",
            previousData = "0",
        ) {

        }

        FinancilityDayPicker (
            title = "Дата",
            previousValue = "..."
        ) {

        }

        FinancilityListItem (
            title = "Время",
            trailingText = "...",
            isClickable = false,
            trailingIcon = R.drawable.ic_light_arrow
        ) {

        }

        FinancilityEditText(
            previousData = "",
            label = "Комментарий",
            isShowTrailingIcon = false,
        ) {

        }



        FinancilityButton(
            text = "Удалить расход",
            onClick = {}
        )
    }
}