package dev.lkey.financility.feature_expenses.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkey.financility.core.ErrorHandler
import dev.lkey.financility.feature_articles.presentation.ArticleAction
import dev.lkey.financility.feature_expenses.domain.usecase.GetExpensesUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExpensesViewModel : ViewModel() {

    private val _state = MutableStateFlow(ExpensesState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    private val _action = MutableSharedFlow<ArticleAction>()
    val action = _action.asSharedFlow()

    private val transactionUseCase = GetExpensesUseCase()

    init {
        loadExpenses()
    }

    fun onEvent(
        event: ExpensesEvent
    ) {
        when (event) {
            ExpensesEvent.OnOpenCreateScreen -> {

            }
        }
    }

    private fun loadExpenses() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val result = transactionUseCase.invoke()

            result
                .onSuccess { res ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            transactions = res
                        )
                    }
                }
                .onFailure { err ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                        )
                    }

                    _action.emit(ArticleAction.ShowSnackBar(ErrorHandler().handleException(err)))
                }
        }
    }
}
