package dev.lkey.financility.feature_bill.presentation.viewmodel

import dev.lkey.financility.feature_bill.domain.model.CurrencyOption

/**
 * События экрана счетов
 * */


sealed class BillEvent {
    data object OnLoadBill : BillEvent()
    data class OnChoseCurrency(val currency: CurrencyOption) : BillEvent()
}
