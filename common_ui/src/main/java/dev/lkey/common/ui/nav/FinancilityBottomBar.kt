package dev.lkey.common.ui.nav

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.lkey.common.navigation.Bar

@Composable
fun FinancilityBottomBar (
    navController : NavController
) {

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        for (bar in Bar.items) {
            val isSelected = bar.route.toString() in currentRoute.toString()
            NavigationBarItem(
                selected = isSelected,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.outline
                ),
                onClick = {
                    navController.navigate(bar.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = bar.icon),
                        contentDescription = bar.title,
                        tint = if (isSelected) MaterialTheme.colorScheme.surfaceTint
                        else MaterialTheme.colorScheme.inverseOnSurface
                    )
                },
                label = {
                    Text(
                        text = bar.title,
                        color = MaterialTheme.colorScheme.inverseOnSurface,
                        fontWeight =
                            if (isSelected) FontWeight(600)
                            else FontWeight(500),
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        letterSpacing = 0.5.sp
                    )
                }
            )
        }
    }
}
