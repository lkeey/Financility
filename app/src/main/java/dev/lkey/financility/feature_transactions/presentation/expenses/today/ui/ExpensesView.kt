package dev.lkey.financility.feature_transactions.presentation.expenses.today.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lkey.financility.R
import dev.lkey.financility.components.item.FinancilityListItem
import dev.lkey.financility.core.converter.toEmoji
import dev.lkey.financility.core.converter.toFormat
import dev.lkey.financility.feature_transactions.presentation.expenses.today.viewmodel.ExpensesState

@Composable
fun ExpensesView (
    modifier: Modifier = Modifier,
    state: ExpensesState,
) {
    val scrollState = rememberScrollState()

    Column (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(
                state = scrollState,
            )
    ){

        if (state.accounts.isNotEmpty()) {
            FinancilityListItem(
                title = "Всего",
                description = null,
                backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
                trailingText = "${state.transactions.sumOf { it.amount.toDouble() }.toFormat()} ${state.accounts[0].currency.toEmoji()}",
                isClickable = false,
            )
        }

        state.transactions.forEach {
            FinancilityListItem(
                trailingIcon = R.drawable.ic_light_arrow,
                emoji = it.categoryModel.emoji,
                title = it.categoryModel.name,
                description = it.comment,
                trailingText = "${it.amount} ${state.accounts[0].currency.toEmoji()}",
                height = 69.dp
            ) {
                /* TODO */
            }
        }
    }
}
