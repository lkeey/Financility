package dev.lkey.storage.data.mappers.category

import dev.lkey.common.core.model.category.CategoryModel
import dev.lkey.storage.data.model.CategoryEntity

fun CategoryEntity.toCategoryModel(): CategoryModel
    = CategoryModel(
        id = id,
        name = name,
        emoji = emoji,
        isIncome = isIncome
    )