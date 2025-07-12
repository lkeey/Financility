package dev.lkey.storage.data.mappers

import dev.lkey.common.core.model.CategoryModel
import dev.lkey.storage.data.model.CategoryEntity

fun CategoryModel.toCategoryEntity(): CategoryEntity
    = CategoryEntity(
    id = id,
    name = name,
    emoji = emoji,
    isIncome = isIncome
)
