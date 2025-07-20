package dev.lkey.articles.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkey.articles.domain.usecase.GetArticlesUseCase
import dev.lkey.common.constants.Constants.ARTICLES_SYNC
import dev.lkey.common.core.model.CategoryModel
import dev.lkey.core.error.ErrorHandler
import dev.lkey.core.error.OfflineDataException
import dev.lkey.core.network.FinancilityResult
import dev.lkey.storage.data.sync.AppSyncStorage
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * VM для экрана статей
 * */

class ArticlesViewModel @Inject constructor(
    private val articlesUseCase : GetArticlesUseCase,
    private val appSyncStorage: AppSyncStorage
) : ViewModel() {

    private val _state = MutableStateFlow(ArticlesState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        _state.value
    )

    private val _action = MutableSharedFlow<ArticleAction>()
    val action = _action.asSharedFlow()

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

            ArticlesEvent.OnLoadArticles -> {
                loadArticles()
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

                    if (err is OfflineDataException) {
                        _state.update {
                            it.copy(
                                status = FinancilityResult.Success,
                                articles = err.data as List<CategoryModel>,
                                lastSync = appSyncStorage.getSyncTime(
                                    feature = ARTICLES_SYNC,
                                )
                            )
                        }
                    } else {
                        _state.update {
                            it.copy(status = FinancilityResult.Error)
                        }

                        _action.emit(ArticleAction.ShowSnackBar(ErrorHandler().handleException(err)))
                    }

                }
        }
    }

}
