package dev.lkey.articles.data.mappers

import dev.lkey.common.core.model.CategoryModel
import dev.lkey.storage.data.model.CategoryEntity

fun CategoryEntity.toCategoryModel(): CategoryModel
    = CategoryModel(
        id = id,
        name = name,
        emoji = emoji,
        isIncome = isIncome
    )