package dev.lkey.financility.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkey.financility.R

@Composable
fun FinancilityEditText (
    previousData: String,
    label: String,
    onTextChanged: (String) -> Unit,
    onTrailingIconClick: (String) -> Unit,
) {
    var textValue by remember {
        mutableStateOf(previousData)
    }

    TextField(
        value = textValue,
        onValueChange = {
            textValue = it
            onTextChanged(it)
        },
        placeholder = {
            Text(
                text = label,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    lineHeight = 24.sp,
                    color = MaterialTheme.colorScheme.surfaceContainer,
                    letterSpacing = 0.5.sp
                )
            )
        },
        shape = RectangleShape,
        singleLine = true,
        modifier = Modifier
//                .fillMaxSize()
            .fillMaxWidth()
            .height(56.dp)
            /*.padding(4.dp)*/,
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.surfaceContainer,
            unfocusedTextColor = MaterialTheme.colorScheme.surfaceContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        trailingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = "Search",
                tint = MaterialTheme.colorScheme.inverseSurface,
                modifier = Modifier
                    .clickable {
                        onTrailingIconClick(textValue)
                    }
            )
        }
    )


}
