package dev.lkey.financility.feature_articles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.lkey.financility.components.FinancilityEditText
import dev.lkey.financility.feature_expenses.data.MockTransactionModel

@Composable
fun ArticlesView (
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
        ),
        MockTransactionModel(
            emoji = "\uD83D\uDC57",
            title = "На собачку",
        ),
        MockTransactionModel(
            emoji = "\uD83D\uDC57",
            title = "Ремонт квартиры",
        ),
        MockTransactionModel(
            emoji = "\uD83D\uDC57",
            title = "Продукты",
        ),
        MockTransactionModel(
            emoji = "\uD83D\uDC57",
            title = "Ремонт квартиры",
        ),
        MockTransactionModel(
            emoji = "\uD83D\uDC57",
            title = "Продукты",
        ),
        MockTransactionModel(
            emoji = "\uD83D\uDC57",
            title = "Спортзал",
        ),
        MockTransactionModel(
            emoji = "\uD83D\uDC57",
            title = "Медицина",
        ),
    )
    Column (
        modifier = modifier
            .fillMaxSize()
    ){
        FinancilityEditText(
            label = "Найти статью",
            previousData = ""
        ) {
            /*TODO*/
        }
    }
}