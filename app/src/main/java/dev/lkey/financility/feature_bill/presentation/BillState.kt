package dev.lkey.financility.feature_bill.presentation

import dev.lkey.financility.core.network.FinancilityResult
import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel

data class BillState (
    val bill : List<AccountBriefModel> = emptyList(),
    val status: FinancilityResult = FinancilityResult.Loading,
)