package dev.lkey.financility.feature_bill.presentation.viewmodel

/**
 * События экрана счетов
 * */


sealed class BillEvent {
    data object OnLoadBill : BillEvent()
    data object OnChoseCurrency : BillEvent()
}
