package dev.lkey.financility.feature_bill.presentation

sealed class BillAction {
    data class ShowSnackBar(val message: String) : BillAction()
}
