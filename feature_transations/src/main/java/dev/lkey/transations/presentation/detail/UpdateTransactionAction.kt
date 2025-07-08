package dev.lkey.transations.presentation.detail

sealed class UpdateTransactionAction {
    data class ShowSnackBar(val message: String) : UpdateTransactionAction()
    data object OnOpenScreen : UpdateTransactionAction()
}
