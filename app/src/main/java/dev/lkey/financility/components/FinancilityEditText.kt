package dev.lkey.financility.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Modifier
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
        singleLine = true,
        modifier = Modifier
            .height(56.dp)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.surfaceContainer,
            unfocusedTextColor = MaterialTheme.colorScheme.surfaceContainer
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