package dev.lkey.financility.components.sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkey.financility.components.item.FinancilityListItem
import dev.lkey.financility.feature_bill.domain.model.CurrencyOption

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinancilityCurrencySheet (
    currencies: List<CurrencyOption>,
    onCurrencyClicked: (CurrencyOption) -> Unit,
    onCloseSheet: () -> Unit
){

    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onCloseSheet,
        sheetState = sheetState,
        containerColor = White,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {

        currencies.forEach { currency ->
            FinancilityListItem(
                title = currency.label,
                emoji = currency.symbol,
                backgroundEmojiColor = White,
                onClick = {
                    onCurrencyClicked(currency)
                }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.tertiaryContainer)
                .clickable { onCloseSheet.invoke() }
                .padding(vertical = 16.dp, horizontal = 16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
//                Icon(Icons.Default.Close, contentDescription = "Отмена", tint = Color.White)

//                Spacer(modifier = Modifier.width(8.dp))

                Text("Отмена", color = White, fontSize = 16.sp)
            }
        }

    }

}
