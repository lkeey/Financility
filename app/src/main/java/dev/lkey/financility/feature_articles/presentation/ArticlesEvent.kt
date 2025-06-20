package dev.lkey.financility.feature_articles.presentation

sealed interface ArticlesEvent {
    data class OnSearchValueChanged(val searchValue: String): ArticlesEvent
    data object OnLoadArticles: ArticlesEvent
}
