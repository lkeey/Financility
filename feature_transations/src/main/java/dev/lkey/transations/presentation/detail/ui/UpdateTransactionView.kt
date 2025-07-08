package dev.lkey.transations.presentation.detail.ui

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
import dev.lkey.transations.presentation.detail.viewmodel.UpdateTransactionEvent
import dev.lkey.transations.presentation.detail.viewmodel.UpdateTransactionState

@Composable
fun UpdateTransactionView (
    modifier: Modifier = Modifier,
    state: UpdateTransactionState,
    isIncome: Boolean,
    onEvent: (UpdateTransactionEvent) -> Unit
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
                    onEvent(UpdateTransactionEvent.OnChoseArticle(it))
                },
            )
        }

        FinancilityNumTextField (
            title = "Сумма",
            previousData = state.sum ?: "",
        ) {
            onEvent(UpdateTransactionEvent.OnUpdateSum(it))
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
            onEvent(UpdateTransactionEvent.OnUpdateDate(it))
        }

        FinancilityTimePicker(
            title = "Выбери время",
            previousValue = state.time
        ) {
            onEvent(UpdateTransactionEvent.OnUpdateTime(it))
        }

        FinancilityEditText(
            previousData = state.comment ?: "",
            label = "Комментарий",
            isShowLeadingIcon = false,
            isShowTrailingIcon = false,
            backgroundColor = MaterialTheme.colorScheme.onSurface
        ) {
            onEvent(UpdateTransactionEvent.OnUpdateComment(it))
        }

        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceDim,
        )

        FinancilityButton(
            text = "Удалить ${if (isIncome) "доход" else "расход"}",
            onClick = {
                onEvent(UpdateTransactionEvent.OnDelete)
            }
        )
    }
}
