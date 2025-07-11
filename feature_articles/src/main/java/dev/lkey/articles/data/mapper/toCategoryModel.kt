package dev.lkey.articles.data.mapper

import dev.lkey.articles.data.model.CategoryEntity
import dev.lkey.common.core.model.CategoryModel

class toCategoryModel {
}

fun CategoryEntity.toCategoryModel(): CategoryModel
    = CategoryModel(
        id = id,
        name = name,
        emoji = emoji,
        isIncome = isIncome
    )