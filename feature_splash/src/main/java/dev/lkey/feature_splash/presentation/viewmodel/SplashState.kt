package dev.lkey.feature_splash.presentation.viewmodel

import dev.lkey.core.network.FinancilityResult

data class SplashState (
    val pin: String = "",
    val isPin: Boolean = false,
    val status: FinancilityResult = FinancilityResult.Loading
)