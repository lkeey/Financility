package dev.lkey.articles.presentation.viewmodel

/**
 * События экрана статей
 * */

sealed interface ArticlesEvent {
    data class OnSearchValueChanged(val searchValue: String): ArticlesEvent
    data object OnLoadArticles: ArticlesEvent
}
