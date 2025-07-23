package dev.lkey.transations.presentation.detail.viewmodel

import dev.lkey.common.core.model.category.CategoryModel
import dev.lkey.common.core.model.transaction.TransactionModel

/**
 * События экрана обновления транзакций
 * */

sealed class UpdateTransactionEvent {
    data class OnChoseArticle(val article: CategoryModel) : UpdateTransactionEvent()
    data class OnUpdateSum(val sum: String) : UpdateTransactionEvent()
    data class OnUpdateDate(val date: String) : UpdateTransactionEvent()
    data class OnUpdateTime(val time: String) : UpdateTransactionEvent()
    data class OnUpdateComment(val comment: String) : UpdateTransactionEvent()
    data class OnLoadData(
        val transaction: TransactionModel,
        val isIncome: Boolean
    ) : UpdateTransactionEvent()
    data object OnUpdate : UpdateTransactionEvent()
    data object OnDelete : UpdateTransactionEvent()
}
