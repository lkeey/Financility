package dev.lkey.financility.feature_articles

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dev.lkey.financility.R
import dev.lkey.financility.components.FinancilityEditText
import dev.lkey.financility.components.ListItem
import dev.lkey.financility.feature_expenses.data.MockTransactionModel

@Composable
fun ArticlesView (
    modifier: Modifier = Modifier
) {
    var searchText by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

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
            .verticalScroll(
                state = scrollState,
            )
    ){
        FinancilityEditText(
            label = "Найти статью",
            previousData = "",
            onTextChanged = {
                /* TODO */
            }
        ) {
            searchText = it
        }

        mockData
            .filter { it.title.contains(searchText) }
            .forEach {
                ListItem(
                    emoji = it.emoji,
                    title = it.title,
                    description = it.description,

                )
        }
    }
}