package dev.lkey.financility.feature_bill.presentation.edit.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import dev.lkey.financility.components.field.FinancilityEditText
import dev.lkey.financility.feature_bill.presentation.edit.viewmodel.EditBillEvent
import dev.lkey.financility.feature_bill.presentation.edit.viewmodel.EditBillState

@Composable
fun EditBillView (
    modifier: Modifier = Modifier,
    state: EditBillState,
    onEvent: (EditBillEvent) -> Unit
) {
    Column (
        modifier = modifier
            .fillMaxSize()
    ) {
        state.accounts.forEach {
            FinancilityEditText(
                previousData = "",
                label = it.name,
                backgroundColor = White,
                isShowTrailingIcon = false,
                isShowLeadingIcon = true,
            ) {
                onEvent(EditBillEvent.OnEnteredBillName(it))
            }
        }

        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceDim,
        )
    }
}
