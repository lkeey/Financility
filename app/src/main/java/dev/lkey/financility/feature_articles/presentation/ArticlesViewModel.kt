package dev.lkey.financility.feature_articles.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkey.financility.feature_articles.domain.usecase.GetArticlesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
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

    private val articlesUseCase = GetArticlesUseCase()

    init {
        viewModelScope.launch {
            val articles = articlesUseCase.invoke()
            _state.update {
                it.copy(
                    articles = articles
                )
            }
        }
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
}
