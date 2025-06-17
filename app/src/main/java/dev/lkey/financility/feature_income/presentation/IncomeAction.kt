package dev.lkey.financility.feature_income.presentation

sealed class IncomeAction {
    data class ShowSnackBar(val message: String) : IncomeAction()
}
