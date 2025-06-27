package dev.lkey.financility.feature_bill.presentation.viewmodel

/**
 * Действия со стороны VM на экран счетов
 * */

sealed class BillAction {
    data class ShowSnackBar(val message: String) : BillAction()
}
