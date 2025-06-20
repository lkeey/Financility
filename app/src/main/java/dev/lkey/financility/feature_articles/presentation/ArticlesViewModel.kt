package dev.lkey.financility.feature_articles.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkey.financility.core.network.ErrorHandler
import dev.lkey.financility.core.network.FinancilityResult
import dev.lkey.financility.feature_articles.domain.usecase.GetArticlesUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArticlesViewModel : ViewModel() {

    private val _state = MutableStateFlow(ArticlesState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    private val _action = MutableSharedFlow<ArticleAction>()
    val action = _action.asSharedFlow()

    private val articlesUseCase = GetArticlesUseCase()

    init {
        loadArticles()
    }

    fun onEvent(
        event: ArticlesEvent
    ) {
        when (event) {
            is ArticlesEvent.OnSearchValueChanged -> {
                _state.update {
                    it.copy(
                        searchValue = event.searchValue
                    )
                }
            }
        }
    }

    private fun loadArticles() {
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
                            status = FinancilityResult.Success,
                            articles = res
                        )
                    }
                }
                .onFailure { err ->
                    _state.update {
                        it.copy(
                            status = FinancilityResult.Error
                        )
                    }

                    _action.emit(ArticleAction.ShowSnackBar(ErrorHandler().handleException(err)))
                }
        }
    }
}
