package dev.lkey.financility.feature_bill.data.repository

import dev.lkey.financility.BuildConfig
import dev.lkey.financility.core.error.ApiException
import dev.lkey.financility.core.network.ktorClient
import dev.lkey.financility.core.network.safeCall
import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel
import dev.lkey.financility.feature_bill.domain.repository.BillRepository
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

/**
 * Репозиторий для получения счетов
 * */

class BillRepositoryImpl : BillRepository {

    override suspend fun getBillInfo(): Result<List<AccountBriefModel>> {
        return safeCall {
            val response: HttpResponse = ktorClient.get("${BuildConfig.BASE_URL}/accounts")

            if (response.status != HttpStatusCode.OK) {
                throw ApiException("Ошибка API: ${response.status}")
            }

            response.body()
        }
    }

}
