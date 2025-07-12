package dev.lkey.bill.data.repository


import dev.lkey.bill.data.mappers.toAccountBriefModel
import dev.lkey.bill.data.mappers.toAccountEntity
import dev.lkey.bill.data.model.UpdateAccountDto
import dev.lkey.bill.domain.repository.BillRepository
import dev.lkey.common.constants.Constants
import dev.lkey.common.core.model.AccountBriefModel
import dev.lkey.core.error.ApiException
import dev.lkey.core.error.OfflineDataException
import dev.lkey.core.network.ktorClient
import dev.lkey.core.network.safeCall
import dev.lkey.storage.data.dao.AccountDao
import dev.lkey.storage.data.sync.AppSyncStorage
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import jakarta.inject.Inject

/**
 * Репозиторий для получения счетов
 * */

class BillRepositoryImpl @Inject constructor(
    private val accountDao: AccountDao,
    private val appSyncStorage: AppSyncStorage
) : BillRepository {

    override suspend fun getBillInfo(): Result<List<AccountBriefModel>> {
        return safeCall {
            try {
                val response: HttpResponse = ktorClient.get("accounts")

                if (response.status != HttpStatusCode.OK) {
                    throw ApiException("Ошибка API: ${response.status}")
                }

                val accounts = response.body<List<AccountBriefModel>>()

                /* save to local DB */
                accountDao.insertAll(accounts.map {
                    it.toAccountEntity()
                })

                /* save last sync */
                appSyncStorage.saveSyncTime(
                    feature = Constants.BILL_SYNC,
                    timestamp = System.currentTimeMillis()
                )

                return@safeCall accounts
            } catch (e: Exception) {

                /* get cashed articles */
                val cached = accountDao.getAll().map {
                    it.toAccountBriefModel()
                }

                /* if not cashed data */
                if (cached.isNotEmpty()) {
                    throw OfflineDataException(cached)
                }

                throw e
            }
        }
    }

    override suspend fun updateBill(
        id: Int,
        newBill: UpdateAccountDto
    ): Result<AccountBriefModel> {
        return safeCall {

            val response: HttpResponse = ktorClient.put("accounts/$id") {
                setBody(newBill)
            }

            if (response.status != HttpStatusCode.OK) {
                throw ApiException("Ошибка API: ${response.status}")
            }

            return@safeCall response.body()

        }
    }

}
