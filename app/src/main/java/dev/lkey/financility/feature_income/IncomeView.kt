package dev.lkey.financility.feature_income

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lkey.financility.R
import dev.lkey.financility.components.ListItem
import dev.lkey.financility.feature_expenses.data.MockTransactionModel

@Composable
fun IncomeView (
    modifier: Modifier = Modifier
) {
    val mockData = listOf(
        MockTransactionModel(
            title = "Зарплата",
            cost = "500 000 ₽"
        ),
        MockTransactionModel(
            title = "Подработка",
            cost = "100 000 ₽"
        ),
    )

    Column (
        modifier = modifier
            .fillMaxSize()
    ){
        ListItem(
            title = "Всего",
            description = null,
            backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
            trailingText = "600 000 ₽"
        )

        mockData.forEach {
            ListItem(
                trailingIcon = R.drawable.ic_light_arrow,
                emoji = it.emoji,
                title = it.title,
                description = it.description,
                trailingText = it.cost,
                height = 72.dp
            )
        }
    }
}