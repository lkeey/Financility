package dev.lkey.financility

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.lkey.financility.ui.theme.FinancilityTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinancilityApp() {
    FinancilityTheme {

        Scaffold (
            bottomBar = {
                BottomAppBar {
                    NavigationBarItem(
                        selected = false,
                        onClick = { /* Navigate */ },
                        icon = {

                        },
                        label = { Text("Home") }
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = { /* Navigate */ },
                        icon = {

                        },
                        label = { Text("Settings") }
                    )
                }
            },
            topBar = {
                TopAppBar(
                    title = { Text("Top Bar") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                )
            },
        ) { padding ->
            Column (
                modifier = Modifier
                    .padding(padding)
            ) {
                Text("hello")
            }
        }
    }

}
