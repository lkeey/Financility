package dev.lkey.bill.presentation.current.viewmodel

/**
 * Действия со стороны VM на экран счетов
 * */

sealed class BillAction {
    data class ShowSnackBar(val message: String) : BillAction()
}
