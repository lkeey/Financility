package dev.lkey.financility.feature_transactions.domain.usecase

import dev.lkey.financility.feature_transactions.domain.model.CategoryModel
import dev.lkey.financility.feature_transactions.domain.repository.TransactionsRepository
import jakarta.inject.Inject

/**
 * Use Case для получения статей
 * */

class GetArticlesUseCase @Inject constructor(
    private val repository: TransactionsRepository
) {

    suspend operator fun invoke(): Result<List<CategoryModel>> {

//        return retryRequest {
//
//        }

        return repository.getArticles()

    }

}
