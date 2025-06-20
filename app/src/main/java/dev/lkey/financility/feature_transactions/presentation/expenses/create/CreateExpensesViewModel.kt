package dev.lkey.financility.feature_transactions.presentation.expenses.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkey.financility.core.network.ApiException
import dev.lkey.financility.core.network.ErrorHandler
import dev.lkey.financility.feature_articles.domain.usecase.GetArticlesUseCase
import dev.lkey.financility.feature_transactions.data.dto.TransactionDto
import dev.lkey.financility.feature_transactions.domain.usecase.GetAccountUseCase
import dev.lkey.financility.feature_transactions.domain.usecase.PostTransactionUseCase
import dev.lkey.financility.feature_transactions.presentation.expenses.today.ExpensesAction
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateExpensesViewModel (
    private val accountUseCase : GetAccountUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CreateExpensesState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    private val _action = MutableSharedFlow<ExpensesAction>()
    val action = _action.asSharedFlow()

    private val articlesUseCase = GetArticlesUseCase()
    private val createTransactionUseCase = PostTransactionUseCase()


    fun onEvent(
        event: CreateExpensesEvent
    ) {
        when (event) {
            is CreateExpensesEvent.OnChoseArticle -> {
                _state.update {
                    it.copy(
                        article = event.article
                    )
                }
            }
            is CreateExpensesEvent.OnEnterComment -> {
                _state.update {
                    it.copy(
                        comment = event.comment
                    )
                }
            }
            is CreateExpensesEvent.OnEnterDate -> {
                _state.update {
                    it.copy(
                        date = event.date
                    )
                }
            }
            is CreateExpensesEvent.OnEnterSum -> {
                _state.update {
                    it.copy(
                        sum = event.sum
                    )
                }
            }
            is CreateExpensesEvent.OnEnterTime -> {
                _state.update {
                    it.copy(
                        time = event.time
                    )
                }
            }

            CreateExpensesEvent.OnLoadData -> {
                loadAccount {
                    loadArticles()
                }
            }

            CreateExpensesEvent.OnSave -> {
                saveExpenses()
            }

        }
    }

    fun loadAccount(
        onSuccess: (Int) -> Unit
    ) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val result = accountUseCase.invoke()

            result
                .onSuccess { res ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            accounts = res
                        )
                    }

                    onSuccess(res[0].id)
                }
                .onFailure { err ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                        )
                    }

                    _action.emit(ExpensesAction.ShowSnackBar(ErrorHandler().handleException(err)))
                }

        }
    }

    fun loadArticles() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val result = articlesUseCase.invoke()

            result
                .onSuccess { res ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            articles = res.filter { !it.isIncome }
                        )
                    }
                }
                .onFailure { err ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                        )
                    }

                    _action.emit(ExpensesAction.ShowSnackBar(ErrorHandler().handleException(err)))
                }
        }
    }

    fun saveExpenses () {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            try {
                val result = createTransactionUseCase.invoke(
                    TransactionDto(
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
                                isLoading = false,
                            )
                        }

                        _action.emit(ExpensesAction.ShowSnackBar("Расход был добавлен"))
                    }
                    .onFailure { err ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                            )
                        }

                        _action.emit(ExpensesAction.ShowSnackBar(ErrorHandler().handleException(err)))
                    }

            } catch (e : Exception) {
                _action.emit(ExpensesAction.ShowSnackBar(ErrorHandler().handleException(e)))
            }

        }
    }
}
