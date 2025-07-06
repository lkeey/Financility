package dev.lkey.transations.presentation.income.today.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lkey.common.R
import dev.lkey.common.ui.item.FinancilityListItem
import dev.lkey.core.converter.toEmoji
import dev.lkey.core.converter.toFormat
import dev.lkey.transations.presentation.income.today.viewmodel.IncomeState

@Composable
fun IncomeView (
    modifier: Modifier = Modifier,
    state: IncomeState
) {

    Column (
        modifier = modifier
            .fillMaxSize()
    ){

        if (state.accounts.isNotEmpty()) {
            FinancilityListItem(
                title = "Всего",
                description = null,
                backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
                trailingText = "${state.transactions.sumOf { it.amount.toDouble() }.toFormat()} ${state.accounts[0].currency.toEmoji()}",
                isClickable = false,
            ) { }
        }

        state.transactions.forEach {
            FinancilityListItem(
                trailingIcon = R.drawable.ic_light_arrow,
                emoji = it.categoryModel.emoji,
                title = it.categoryModel.name,
                description = it.comment,
                trailingText = "${it.amount} ${state.accounts[0].currency.toEmoji()}",
                height = 72.dp
            ) {
                /* TODO */
            }
        }
    }
}