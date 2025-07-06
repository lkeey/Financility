package dev.lkey.articles.presentation.viewmodel

/**
 * Действия со стороны VM на экран статей
 * */

sealed class ArticleAction {
    data class ShowSnackBar(val message: String) : ArticleAction()
}
