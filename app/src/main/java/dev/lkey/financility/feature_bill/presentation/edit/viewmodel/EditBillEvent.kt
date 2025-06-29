package dev.lkey.financility.feature_bill.presentation.edit.viewmodel

import dev.lkey.financility.feature_bill.presentation.current.viewmodel.BillEvent

/**
 * События экрана редактирования счетов
 * */

sealed class EditBillEvent {
    data object OnLoadBill : EditBillEvent()
    data class OnEnteredBillName(val name: String) : EditBillEvent()
    data object OnSaveBill : EditBillEvent()
}
