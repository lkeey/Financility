package dev.lkey.financility.feature_bill.presentation.current.viewmodel

/**
 * Действия со стороны VM на экран счетов
 * */

sealed class BillAction {
    data class ShowSnackBar(val message: String) : BillAction()
}
