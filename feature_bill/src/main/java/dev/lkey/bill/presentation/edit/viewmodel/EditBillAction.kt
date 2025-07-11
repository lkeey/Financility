package dev.lkey.bill.presentation.edit.viewmodel

/**
 * Действия со стороны VM на экран редактирования счетов
 * */

sealed class EditBillAction {
    data class ShowSnackBar(val message: String) : EditBillAction()
    data object OnOpenBill : EditBillAction()
}