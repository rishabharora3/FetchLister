package com.project.data.repository

import com.project.data.model.asEntity
import com.project.data.util.NetworkMonitor
import com.project.database.dao.CharacterDetailDao
import com.project.database.model.asExternalModel
import com.project.model.data.CharacterDetail
import com.project.network.FetchListerNetworkDataSource
import com.project.network.model.NetworkCharacterDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OfflineFirstCharacterDetailRepository @Inject constructor(
    private val characterDetailDao: CharacterDetailDao,
    private val network: FetchListerNetworkDataSource,
    private val networkMonitor: NetworkMonitor,
) : CharacterDetailRepository {
    override fun getCharacterDetail(characterId: String): Flow<CharacterDetail> = flow {
        val characterDetail: CharacterDetail? =
            characterDetailDao.getCharacterDetail(characterId).map { characterDetailEntity ->
                characterDetailEntity?.asExternalModel()
            }.first()

        // Emit the cached value first if available
        characterDetail?.let {
            emit(it)
        }

        // If online, fetch from remote and emit
        when {
            networkMonitor.isOnline.first() -> {
                val networkCharacterDetail = network.getCharacterDetail(characterId).getOrElse {
                    NetworkCharacterDetail(id = "")
                }
                characterDetailDao.upsertCharacterDetail(networkCharacterDetail.asEntity())
                emit(networkCharacterDetail.asEntity().asExternalModel())
            }

            characterDetail == null -> {
                emit(NetworkCharacterDetail(id = "").asEntity().asExternalModel())
            }
        }
    }
}

