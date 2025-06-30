package dev.lkey.financility.feature_bill.presentation.current.viewmodel

import dev.lkey.financility.feature_bill.domain.model.CurrencyOption

/**
 * События экрана счетов
 * */


sealed class BillEvent {
    data object OnLoadBill : BillEvent()
}
