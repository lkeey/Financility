package dev.lkey.financility.feature_transactions.presentation.expenses.create.ui

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
import dev.lkey.financility.components.btn.FinancilityButton
import dev.lkey.financility.components.field.FinancilityDayPicker
import dev.lkey.financility.components.field.FinancilityDropDown
import dev.lkey.financility.components.field.FinancilityEditText
import dev.lkey.financility.components.item.FinancilityListItem
import dev.lkey.financility.components.field.FinancilityNumTextField
import dev.lkey.financility.components.field.FinancilityTimePicker
import dev.lkey.financility.feature_transactions.presentation.expenses.create.viewmodel.CreateExpensesEvent
import dev.lkey.financility.feature_transactions.presentation.expenses.create.viewmodel.CreateExpensesState

@Composable
fun CreateExpensesView (
    modifier: Modifier = Modifier,
    state: CreateExpensesState,
    onEvent: (CreateExpensesEvent) -> Unit
) {
    Column (
        modifier = modifier
            .fillMaxSize()
    ){
        if (state.accounts.isNotEmpty()) {
            FinancilityListItem (
                title = "Счет",
                trailingText = state.accounts[0].name,
                trailingIcon = R.drawable.ic_light_arrow
            )
        }

        if (state.articles.isNotEmpty()) {
            FinancilityDropDown(
                title = "Статья",
                options = state.articles,
                previousData = state.article?.name ?: "",
                onOptionSelected = {
                    onEvent(CreateExpensesEvent.OnChoseArticle(it))
                },
            )
        }

        FinancilityNumTextField (
            title = "Сумма",
            previousData = state.sum ?: "",
        ) {
            onEvent(CreateExpensesEvent.OnEnterSum(it))
        }

        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceDim,
        )

        FinancilityDayPicker (
            title = "Дата",
            previousValue = state.date
        ) {
            onEvent(CreateExpensesEvent.OnEnterDate(it))
        }

        FinancilityTimePicker(
            title = "Выбери время",
            previousValue = state.time
        ) {
            onEvent(CreateExpensesEvent.OnEnterTime(it))
        }

        FinancilityEditText(
            previousData = "",
            label = "Комментарий",
            isShowTrailingIcon = false,
            backgroundColor = MaterialTheme.colorScheme.onSurface
        ) {
            onEvent(CreateExpensesEvent.OnEnterComment(it))
        }

        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceDim,
        )


        FinancilityButton(
            text = "Добавить расход",
            backgroundColor = MaterialTheme.colorScheme.primary,
            onClick = {
                onEvent(CreateExpensesEvent.OnSave)
            }
        )
    }
}