package dev.lkey.financility.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkey.financility.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinancilityDropDown (
    title: String,
    options: List<String>,
    previousData: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    var textValue by remember {
        mutableStateOf(previousData)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
    ) {

//        TextField(
//            modifier = modifier
//                .menuAnchor()
//                .fillMaxWidth(),
//            label = {
//                Text(
//                    text = title,
//                    fontWeight = FontWeight.W400,
//                    fontSize = 16.sp,
//                    lineHeight = 24.sp,
//                    letterSpacing = 0.5.sp,
//                    color = MaterialTheme.colorScheme.inverseOnSurface
//                )
//            },
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedBorderColor = MaterialTheme.colorScheme.primary,
//                unfocusedBorderColor = MaterialTheme.colorScheme.surfaceVariant,
//                focusedContainerColor = Color.Transparent,
//                unfocusedContainerColor = Color.Transparent,
//                focusedTextColor = MaterialTheme.colorScheme.surfaceContainer,
//                unfocusedTextColor = MaterialTheme.colorScheme.surfaceContainer,
//            ),
//            keyboardOptions = KeyboardOptions(
//                imeAction = ImeAction.Next
//            ),
//            trailingIcon = {
//                Icon(
//                    painter = painterResource(R.drawable.ic_light_arrow),
//                    contentDescription = "список",
//                    modifier = Modifier
//                        .size(24.dp)
//                )
//            },
//            shape = RoundedCornerShape(16.dp),
//            singleLine = true,
//            maxLines = 1,
//            value = textValue,
//            onValueChange = { },
//            readOnly = true,
//        )

        FinancilityListItem(
            title = title,
            trailingText = textValue,
            modifier = modifier
                .menuAnchor(),
            trailingIcon = R.drawable.ic_light_arrow
        ) {

        }

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = selectionOption,
                            color = MaterialTheme.colorScheme.inverseOnSurface
                        )
                    },
                    onClick = {
                        textValue = selectionOption
                        expanded = false
                        onOptionSelected(selectionOption)
                    },
                )
            }
        }
    }
}
