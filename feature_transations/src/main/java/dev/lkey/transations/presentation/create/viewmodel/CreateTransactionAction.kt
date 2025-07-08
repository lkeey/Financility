package dev.lkey.transations.presentation.create.viewmodel

/**
 * Действия со стороны VM на экран добавления расходов
 * */

sealed class CreateTransactionAction {
    data class ShowSnackBar(val message: String) : CreateTransactionAction()
    data object OnOpenScreen : CreateTransactionAction()
}
