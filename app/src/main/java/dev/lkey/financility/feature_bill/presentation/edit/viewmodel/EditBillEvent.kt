package dev.lkey.financility.feature_bill.presentation.edit.viewmodel

import dev.lkey.common.core.model.CurrencyOption

/**
 * События экрана редактирования счетов
 * */

sealed class EditBillEvent {
    data object OnLoadBill : EditBillEvent()
    data class OnChoseCurrency(val currency: CurrencyOption) : EditBillEvent()
    data class OnEnteredBillName(val name: String) : EditBillEvent()
    data class OnEnteredAmount(val amount: String) : EditBillEvent()
    data object OnSaveBill : EditBillEvent()
}
