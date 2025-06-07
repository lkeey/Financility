package dev.lkey.financility.feature_expenses

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import dev.lkey.financility.R
import dev.lkey.financility.components.ListItem

@Composable
fun ExpensesView (
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxSize()
    ){
        ListItem(
            image = {
                Image(
                    painter = painterResource(R.drawable.ic_expenses),
                    contentDescription = "awdawd"
                )
            },
            trailingIcon = {
                Image(
                    painter = painterResource(R.drawable.ic_expenses),
                    contentDescription = "awdawd"
                )
            },
            title = "Аренда",
            description = "Описание",
            backgroundColor = Color.Blue,
            cost = 1000
        )
    }
}
