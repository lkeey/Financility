package dev.lkey.bill.presentation.current.viewmodel

/**
 * События экрана счетов
 * */


sealed class BillEvent {
    data object OnLoadBill : BillEvent()
}
