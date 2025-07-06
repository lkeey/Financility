package dev.lkey.financility.feature_bill.presentation.current.viewmodel

/**
 * События экрана счетов
 * */


sealed class BillEvent {
    data object OnLoadBill : BillEvent()
}
