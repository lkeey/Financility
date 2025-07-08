package dev.lkey.transations.presentation.create.viewmodel

import dev.lkey.common.core.model.CategoryModel

/**
 * События экрана добавления расходов
 * */

sealed class CreateTransactionEvent {
    data class OnChoseArticle(val article: CategoryModel) : CreateTransactionEvent()
    data class OnEnterSum(val sum: String) : CreateTransactionEvent()
    data class OnEnterDate(val date: String) : CreateTransactionEvent()
    data class OnEnterTime(val time: String) : CreateTransactionEvent()
    data class OnEnterComment(val comment: String) : CreateTransactionEvent()
    data class OnLoadData(val isIncome: Boolean) : CreateTransactionEvent()
    data object OnSave : CreateTransactionEvent()
}
