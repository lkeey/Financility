package dev.lkey.common.ui.field

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dev.lkey.common.R
import dev.lkey.common.core.model.category.CategoryModel
import dev.lkey.common.ui.item.FinancilityListItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinancilityDropDown (
    title: String,
    options: List<CategoryModel>,
    previousData: String,
    onOptionSelected: (CategoryModel) -> Unit,
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
        FinancilityListItem(
            title = title,
            trailingText = textValue,
            modifier = modifier
                .menuAnchor(MenuAnchorType.PrimaryEditable, true),
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
                            text = selectionOption.name,
                            color = MaterialTheme.colorScheme.inverseOnSurface
                        )
                    },
                    onClick = {
                        textValue = selectionOption.name
                        expanded = false
                        onOptionSelected(selectionOption)
                    },
                )
            }
        }
    }
}
