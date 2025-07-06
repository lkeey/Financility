package dev.lkey.transations.domain.usecase

import dev.lkey.common.core.model.CategoryModel
import dev.lkey.core.network.retryRequest
import dev.lkey.transations.domain.repository.TransactionsRepository
import jakarta.inject.Inject

/**
 * Use Case для получения статей
 * */

class GetArticlesUseCase @Inject constructor(
    private val repository: TransactionsRepository
) {

    suspend operator fun invoke(): Result<List<CategoryModel>> {

        return retryRequest {
            repository.getArticles()
        }

    }

}
