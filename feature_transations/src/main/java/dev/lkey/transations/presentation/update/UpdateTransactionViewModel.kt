package dev.lkey.transations.presentation.update

import androidx.lifecycle.ViewModel
import dev.lkey.transations.domain.usecase.GetAccountUseCase
import dev.lkey.transations.domain.usecase.GetArticlesUseCase

class UpdateTransactionViewModel (
    private val accountUseCase : GetAccountUseCase,
    private val articlesUseCase : GetArticlesUseCase,
) : ViewModel() {

}
