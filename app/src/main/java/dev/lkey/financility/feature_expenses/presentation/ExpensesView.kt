package dev.lkey.financility.feature_expenses.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.lkey.financility.R
import dev.lkey.financility.components.ListItem
import dev.lkey.financility.feature_expenses.data.MockTransactionModel

@Composable
fun ExpensesView (
    modifier: Modifier = Modifier
) {

    val mockData = listOf(
        MockTransactionModel(
            emoji = "\uD83D\uDC57",
            title = "Аренда квартиры",
        ),
        MockTransactionModel(
            emoji = "\uD83D\uDC57",
            title = "Одежда",
        ),
        MockTransactionModel(
            emoji = "\uD83D\uDC57",
            title = "На собачку",
            description = "Джек"
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
            trailingText = "436 000 ₽"
        )

        mockData.forEach {
            ListItem(
                trailingIcon = R.drawable.ic_light_arrow,
                emoji = it.emoji,
                title = it.title,
                description = it.description,
                trailingText = it.cost
            )
        }
    }
}
