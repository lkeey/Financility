package dev.lkey.financility.feature_articles.presentation.viewmodel

/**
 * События экрана статей
 * */

sealed interface ArticlesEvent {
    data class OnSearchValueChanged(val searchValue: String): ArticlesEvent
    data object OnLoadArticles: ArticlesEvent
}
