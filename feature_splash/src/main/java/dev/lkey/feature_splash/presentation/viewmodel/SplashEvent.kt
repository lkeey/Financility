package dev.lkey.feature_splash.presentation.viewmodel

sealed class SplashEvent {
    data object OnLoadData : SplashEvent()
}
