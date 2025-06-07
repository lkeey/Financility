package dev.lkey.financility

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import dev.lkey.financility.navigation.Bar
import dev.lkey.financility.ui.theme.FinancilityTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinancilityApp() {
    FinancilityTheme {

        Scaffold (
            bottomBar = {
                BottomAppBar (
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    for (bar in Bar.items) {
                        NavigationBarItem(
                            selected = false,
                            onClick = { /* Navigate */ },
                            icon = {
                                Icon(
                                    painter = painterResource(id = bar.icon),
                                    contentDescription = bar.title
                                )
                            },
                            label = { Text(bar.title) }
                        )
                    }
                }
            },
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = "Top Bar",
                            textAlign = TextAlign.Center
                        )
                    },
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
