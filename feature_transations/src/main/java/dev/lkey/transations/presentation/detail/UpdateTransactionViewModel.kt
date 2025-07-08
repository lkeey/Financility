package dev.lkey.transations.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkey.core.error.ApiException
import dev.lkey.core.error.ErrorHandler
import dev.lkey.core.network.FinancilityResult
import dev.lkey.transations.data.dto.TransactionDto
import dev.lkey.transations.domain.usecase.GetArticlesUseCase
import dev.lkey.transations.domain.usecase.UpdateTransactionUseCase
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Действия со стороны VM на экран редактирования транзакций
 * */

class UpdateTransactionViewModel @Inject constructor (
    private val articlesUseCase : GetArticlesUseCase,
    private val updateUseCase : UpdateTransactionUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(UpdateTransactionState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        _state.value
    )

    private val _action = MutableSharedFlow<UpdateTransactionAction>()
    val action = _action.asSharedFlow()

    fun onEvent(
        event: UpdateTransactionEvent
    ) {
        when (event) {
            is UpdateTransactionEvent.OnChoseArticle -> {
                _state.update {
                    it.copy(
                        article = event.article
                    )
                }
            }
            is UpdateTransactionEvent.OnUpdateComment ->  {
                _state.update {
                    it.copy(
                        comment = event.comment
                    )
                }
            }
            is UpdateTransactionEvent.OnUpdateDate -> {
                _state.update {
                    it.copy(
                        date = event.date
                    )
                }
            }
            is UpdateTransactionEvent.OnUpdateSum -> {
                _state.update {
                    it.copy(
                        sum = event.sum
                    )
                }
            }
            is UpdateTransactionEvent.OnUpdateTime -> {
                _state.update {
                    it.copy(
                        time = event.time
                    )
                }
            }
            is UpdateTransactionEvent.OnLoadData -> {
                _state.update {
                    it.copy(
                        id = event.transaction.id,
                        accounts = listOf(event.transaction.account),
                        article = event.transaction.categoryModel,
                        sum = event.transaction.amount,
                        date = event.transaction.transactionDate.split("T")[0],
                        time = event.transaction.transactionDate.split("T")[1].substring(0, 5),
                        comment = event.transaction.comment
                    )
                }

                loadArticles(
                    isIncome = event.isIncome
                )
            }

            UpdateTransactionEvent.OnUpdate -> {
                updateTransaction()
            }
        }
    }

    private fun updateTransaction() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    status = FinancilityResult.Loading
                )
            }

            val result = updateUseCase.invoke(
                id = state.value.id,
                transaction = TransactionDto(
                    accountId = state.value.accounts[0].id,
                    categoryId = state.value.article?.id ?: throw ApiException("Заполните все поля"),
                    amount = state.value.sum ?: throw ApiException("Заполните все поля"),
                    transactionDate = "${state.value.date}T${state.value.time}:00.000Z",
                    comment = state.value.comment
                )
            )

            result
                .onSuccess { res ->
                    _state.update {
                        it.copy(
                            status = FinancilityResult.Success
                        )
                    }

                    _action.emit(UpdateTransactionAction.OnOpenScreen)
                }
                .onFailure { err ->
                    _state.update {
                        it.copy(
                            status = FinancilityResult.Error
                        )
                    }

                    _action.emit(UpdateTransactionAction.ShowSnackBar(ErrorHandler().handleException(err)))
                }

        }
    }

    fun loadArticles(
        isIncome : Boolean
    ) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    status = FinancilityResult.Loading
                )
            }

            val result = articlesUseCase.invoke()

            result
                .onSuccess { res ->
                    _state.update {
                        it.copy(
                            articles = res.filter { it.isIncome == isIncome },
                            status = FinancilityResult.Success
                        )
                    }
                }
                .onFailure { err ->
                    _state.update {
                        it.copy(
                            status = FinancilityResult.Error
                        )
                    }

                    _action.emit(UpdateTransactionAction.ShowSnackBar(ErrorHandler().handleException(err)))
                }
        }
    }

}
