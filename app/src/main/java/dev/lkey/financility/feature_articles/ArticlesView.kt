package dev.lkey.financility.feature_articles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lkey.financility.components.FinancilityEditText

@Composable
fun ArticlesView (
    modifier: Modifier = Modifier
) {
    var searchText by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

//    var transactions by remember { mutableStateOf<List<TransactionModel>?>(null) }
//
//    LaunchedEffect(Unit) {
//        transactions = RemoteTransactionDataSourceImpl().getTodayTransactions()
//    }

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

        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceDim,
        )

//        transactions
//            ?.filter { it.categoryModel.name.contains(searchText) }
//            ?.forEach {
//                ListItem(
//                    emoji = it.categoryModel.emoji,
//                    title = it.categoryModel.name,
//                    height = 70.dp
//                )
//        }
    }
}