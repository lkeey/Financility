package dev.lkey.financility.feature_expenses.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lkey.financility.R
import dev.lkey.financility.components.ListItem
import dev.lkey.financility.feature_expenses.data.MockTransactionModel
import dev.lkey.financility.feature_expenses.data.TransactionResponseModel
import dev.lkey.financility.feature_expenses.data.network.RemoteTransactionDataSourceImpl
import kotlinx.coroutines.coroutineScope

@Composable
fun ExpensesView (
    modifier: Modifier = Modifier
) {
    var transactions by remember { mutableStateOf<List<TransactionResponseModel>?>(null) }
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        transactions = RemoteTransactionDataSourceImpl().getTodayTransactions()
    }

    Column (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(
                state = scrollState,
            )
    ){

        ListItem(
            title = "Всего",
            description = null,
            backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
            trailingText = "436 558 ₽"
        )

        transactions?.forEach {
            ListItem(
                trailingIcon = R.drawable.ic_light_arrow,
                emoji = it.categoryModel.emoji,
                title = it.categoryModel.name,
                description = it.comment,
                trailingText = it.amount,
                height = 69.dp
            )
        }
    }
}
