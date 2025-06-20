package dev.lkey.financility.feature_transactions.presentation.expenses.create

import dev.lkey.financility.feature_transactions.domain.model.CategoryModel

sealed class CreateExpensesEvent {
    data class OnChoseArticle(val article: CategoryModel) : CreateExpensesEvent()
    data class OnEnterSum(val sum: String) : CreateExpensesEvent()
    data class OnEnterDate(val date: String) : CreateExpensesEvent()
    data class OnEnterTime(val time: String) : CreateExpensesEvent()
    data class OnEnterComment(val comment: String) : CreateExpensesEvent()
    data object OnLoadData : CreateExpensesEvent()
    data object OnSave : CreateExpensesEvent()
}