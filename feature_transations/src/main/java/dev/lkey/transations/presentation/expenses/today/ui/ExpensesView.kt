package dev.lkey.transations.presentation.expenses.today.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lkey.common.R
import dev.lkey.common.core.model.TransactionModel
import dev.lkey.common.ui.item.FinancilityListItem
import dev.lkey.common.ui.item.FinancilitySyncMessage
import dev.lkey.core.converter.toEmoji
import dev.lkey.core.converter.toFormat
import dev.lkey.transations.presentation.expenses.today.viewmodel.ExpensesState

@Composable
fun ExpensesView (
    modifier: Modifier = Modifier,
    state: ExpensesState,
    onItemClick: (TransactionModel) -> Unit,
) {
    val scrollState = rememberScrollState()

    Column (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(
                state = scrollState,
            )
    ){

        state.lastSync?.let {
            FinancilitySyncMessage(it)
        }

        if (state.accounts.isNotEmpty()) {
            FinancilityListItem(
                title = "Всего",
                description = null,
                backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
                trailingText = buildString {
                    append(state.transactions.sumOf { it.amount.toDouble() }.toFormat())
                    append(" ")
                    append(state.accounts[0].currency.toEmoji())
                },
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
                onItemClick(it)
            }
        }
    }
}
