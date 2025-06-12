package dev.lkey.financility.feature_income.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lkey.financility.R
import dev.lkey.financility.components.FinancilityListItem
import dev.lkey.financility.feature_income.presentation.IncomeState

@Composable
fun IncomeView (
    modifier: Modifier = Modifier,
    state: IncomeState
) {

    Column (
        modifier = modifier
            .fillMaxSize()
    ){
        FinancilityListItem(
            title = "Всего",
            description = null,
            backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
            trailingText = "600 000 ₽"
        )

        state.transactions.forEach {
            FinancilityListItem(
                trailingIcon = R.drawable.ic_light_arrow,
                emoji = null,
                title = it.categoryModel.name,
                description = null,
                trailingText = it.amount,
                height = 72.dp
            )
        }
    }
}