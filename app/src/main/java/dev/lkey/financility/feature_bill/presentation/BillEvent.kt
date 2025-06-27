package dev.lkey.financility.feature_bill.presentation

sealed class BillEvent {
    data object OnLoadBill : BillEvent()
}
