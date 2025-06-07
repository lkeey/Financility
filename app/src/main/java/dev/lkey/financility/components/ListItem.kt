package dev.lkey.financility.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ListItem (
    image: @Composable () -> Unit,
    trailingIcon: @Composable () -> Unit,
    title: String,
    description: String? = "",
    cost: Int,
    backgroundColor: Color,
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        image.invoke()

        Column {
            Text(
                title
            )

            description?.let {
                Text(
                    it
                )
            }
        }

        Text(
            "$cost Р"
        )

        trailingIcon.invoke()
    }
}