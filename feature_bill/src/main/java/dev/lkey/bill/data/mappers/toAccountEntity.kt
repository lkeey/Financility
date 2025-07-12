package dev.lkey.bill.data.mappers

import dev.lkey.common.core.model.AccountBriefModel
import dev.lkey.storage.data.model.AccountEntity

fun AccountBriefModel.toAccountEntity(): AccountEntity
        = AccountEntity(
            id = id,
            name = name,
            balance = balance,
            currency = currency
        )
