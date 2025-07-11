package dev.lkey.articles.data.mapper

import dev.lkey.articles.data.model.CategoryEntity
import dev.lkey.common.core.model.CategoryModel

fun CategoryModel.toCategoryEntity(): CategoryEntity
    = CategoryEntity(
        id = id,
        name = name,
        emoji = emoji,
        isIncome = isIncome
    )
