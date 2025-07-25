package dev.lkey.storage.data.mappers.category

import dev.lkey.common.core.model.category.CategoryModel
import dev.lkey.storage.data.model.CategoryEntity

fun CategoryModel.toCategoryEntity(): CategoryEntity
    = CategoryEntity(
    id = id,
    name = name,
    emoji = emoji,
    isIncome = isIncome
)
