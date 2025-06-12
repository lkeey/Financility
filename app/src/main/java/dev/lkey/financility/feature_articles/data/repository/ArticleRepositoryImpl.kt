package dev.lkey.financility.feature_articles.data.repository

import dev.lkey.financility.feature_articles.domain.repository.ArticlesRepository
import dev.lkey.financility.feature_expenses.domain.model.CategoryModel

class ArticleRepositoryImpl : ArticlesRepository {

    override suspend fun getArticles(): List<CategoryModel> {
        /* TODO API */

        return listOf(
            CategoryModel(
                id = 0,
                name = "Аренда квартиры",
                emoji = "\uD83C\uDFE1",
                isIncome = true
            ),
            CategoryModel(
                id = 0,
                name = "Одежда",
                emoji = "\uD83D\uDC57",
                isIncome = true
            ),
            CategoryModel(
                id = 0,
                name = "На собачку",
                emoji = "\uD83D\uDC36",
                isIncome = true
            ),
            CategoryModel(
                id = 0,
                name = "На собачку",
                emoji = "\uD83D\uDC36",
                isIncome = true
            ),
            CategoryModel(
                id = 0,
                name = "Ремонт квартиры",
                emoji = "РК",
                isIncome = true
            ),
            CategoryModel(
                id = 0,
                name = "Продукты",
                emoji = "\uD83C\uDF6D",
                isIncome = true
            ),
            CategoryModel(
                id = 0,
                name = "Спортзал",
                emoji = "\uD83C\uDFCB\uFE0F",
                isIncome = true
            ),
            CategoryModel(
                id = 0,
                name = "Медицина",
                emoji = "\uD83D\uDC8A",
                isIncome = true
            ),
        )
    }

}