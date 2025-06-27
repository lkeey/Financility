package dev.lkey.financility.navigation.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import dev.lkey.financility.navigation.Route
import kotlinx.coroutines.delay

@Composable
fun SplashScreen (
    navController: NavController
) {
    LaunchedEffect(Unit) {
        delay(2000)

        navController.navigate(Route.Expenses) {
            popUpTo(Route.Splash) {
                inclusive = true
            }
        }
    }

    SplashView()
}