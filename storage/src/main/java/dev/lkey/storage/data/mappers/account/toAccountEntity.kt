package dev.lkey.storage.data.mappers.account

import dev.lkey.common.core.model.account.AccountBriefModel
import dev.lkey.storage.data.model.AccountEntity

fun AccountBriefModel.toAccountEntity(): AccountEntity
        = AccountEntity(
            id = id,
            name = name,
            balance = balance,
            currency = currency
        )
