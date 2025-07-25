package dev.lkey.transations.presentation.create.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lkey.common.R
import dev.lkey.common.ui.btn.FinancilityButton
import dev.lkey.common.ui.field.FinancilityDayPicker
import dev.lkey.common.ui.field.FinancilityDropDown
import dev.lkey.common.ui.field.FinancilityEditText
import dev.lkey.common.ui.field.FinancilityNumTextField
import dev.lkey.common.ui.field.FinancilityTimePicker
import dev.lkey.common.ui.item.FinancilityListItem
import dev.lkey.transations.presentation.create.viewmodel.CreateTransactionEvent
import dev.lkey.transations.presentation.create.viewmodel.CreateTransactionState

@Composable
fun CreateTransactionView (
    modifier: Modifier = Modifier,
    state: CreateTransactionState,
    isIncome: Boolean,
    onEvent: (CreateTransactionEvent) -> Unit
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
                    onEvent(CreateTransactionEvent.OnChoseArticle(it))
                },
            )
        }

        FinancilityNumTextField (
            title = "Сумма",
            previousData = state.sum ?: "",
        ) {
            onEvent(CreateTransactionEvent.OnEnterSum(it))
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
            onEvent(CreateTransactionEvent.OnEnterDate(it))
        }

        FinancilityTimePicker(
            title = "Выбери время",
            previousValue = state.time
        ) {
            onEvent(CreateTransactionEvent.OnEnterTime(it))
        }

        FinancilityEditText(
            previousData = state.comment ?: "",
            label = "Комментарий",
            isShowLeadingIcon = false,
            isShowTrailingIcon = false,
            backgroundColor = MaterialTheme.colorScheme.onSurface
        ) {
            onEvent(CreateTransactionEvent.OnEnterComment(it))
        }

        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceDim,
        )

        FinancilityButton(
            text = "Добавить ${if (isIncome) "доход" else "расход"}",
            backgroundColor = MaterialTheme.colorScheme.primary,
            onClick = {
                onEvent(CreateTransactionEvent.OnSave)
            }
        )
    }
}
