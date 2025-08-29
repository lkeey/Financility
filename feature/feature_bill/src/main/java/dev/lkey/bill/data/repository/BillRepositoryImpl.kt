package dev.lkey.bill.data.repository

import dev.lkey.bill.data.model.UpdateAccountDto
import dev.lkey.bill.domain.repository.BillRepository
import dev.lkey.common.core.model.account.AccountBriefModel
import dev.lkey.core.error.ApiException
import dev.lkey.core.network.ktorClient
import dev.lkey.core.network.safeCall
import io.ktor.client.call.body
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import jakarta.inject.Inject

/**
 * Репозиторий для обновления счетов
 * */

class BillRepositoryImpl @Inject constructor() : BillRepository {

    override suspend fun updateBill(
        id: Int,
        newBill: UpdateAccountDto
    ): Result<AccountBriefModel> {
        return safeCall {

            val response: HttpResponse = ktorClient.put("accounts/$id") {
                setBody(newBill)
            }

            if (response.status != HttpStatusCode.Companion.OK) {
                throw ApiException("Ошибка API: ${response.status}")
            }

            return@safeCall response.body()

        }
    }

}
