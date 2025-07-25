package dev.lkey.account.data

import dev.lkey.account.domain.AccountRepository
import dev.lkey.common.constants.Constants
import dev.lkey.common.core.model.account.AccountBriefModel
import dev.lkey.core.error.ApiException
import dev.lkey.core.error.OfflineDataException
import dev.lkey.core.network.ktorClient
import dev.lkey.core.network.safeCall
import dev.lkey.storage.data.dao.AccountDao
import dev.lkey.storage.data.mappers.account.toAccountBriefModel
import dev.lkey.storage.data.mappers.account.toAccountEntity
import dev.lkey.storage.data.sync.AppSyncStorage
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import jakarta.inject.Inject

/**
 * Репозиторий для получения счетов
 * */

class AccountRepositoryImpl @Inject constructor(
    private val accountDao: AccountDao,
    private val appSyncStorage: AppSyncStorage
) : AccountRepository {

    /**
     * Может выбросить [OfflineDataException]
     * использовать только там, где нужно показать, что это оффлайн запрос
     */
    override suspend fun getRemoteAccounts(): Result<List<AccountBriefModel>> {
        return safeCall {
            try {
                val response: HttpResponse = ktorClient.get("accounts")

                if (response.status != HttpStatusCode.Companion.OK) {
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

                /* get cashed accounts */
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

    override suspend fun getCashedAccounts(): Result<List<AccountBriefModel>> {
        return safeCall {

            val cached = accountDao.getAll().map {
                it.toAccountBriefModel()
            }

            if (cached.isNotEmpty()) {
                return@safeCall cached
            } else {
                val response: HttpResponse = ktorClient.get("accounts")

                if (response.status != HttpStatusCode.Companion.OK) {
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
            }
        }
    }
}
