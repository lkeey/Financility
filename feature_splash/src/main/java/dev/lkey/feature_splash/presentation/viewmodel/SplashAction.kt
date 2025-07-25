package dev.lkey.feature_splash.presentation.viewmodel

sealed class SplashAction {
    object OnOpenMainScreen : SplashAction()
    data class ShowSnackBar(val message: String) : SplashAction()
}
