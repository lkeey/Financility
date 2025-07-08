package dev.lkey.transations.presentation.update

sealed class UpdateTransactionAction {
    data class ShowSnackBar(val message: String) : UpdateTransactionAction()
    data object OnOpenScreen : UpdateTransactionAction()
}
