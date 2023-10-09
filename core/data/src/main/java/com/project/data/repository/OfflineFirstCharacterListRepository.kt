package com.project.data.repository

import com.project.data.model.asEntity
import com.project.data.util.NetworkMonitor
import com.project.database.dao.CharacterListItemDao
import com.project.database.model.CharacterListItemEntity
import com.project.database.model.asExternalModel
import com.project.model.data.CharacterListItem
import com.project.network.FetchListerNetworkDataSource
import com.project.network.model.NetworkCharacterListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class OfflineFirstCharacterListRepository @Inject constructor(
    private val characterListItemDao: CharacterListItemDao,
    private val network: FetchListerNetworkDataSource,
    private val networkMonitor: NetworkMonitor,
) : CharacterListRepository {

    override fun getCharacterList(page: Int): Flow<List<CharacterListItem>> = flow {
        val offlineList: List<CharacterListItem> = characterListItemDao.getCharacterList().map {
            it.map(CharacterListItemEntity::asExternalModel)
        }.first()

        // Emit the cached value first if available
        if (offlineList.isNotEmpty()) {
            emit(offlineList)
        }

        when {
            // If online, fetch from remote and emit
            networkMonitor.isOnline.first() -> {
                val networkCharacterList = network.getCharacterList(page).getOrElse {
                    emptyList()
                }
                characterListItemDao.upsertCharacterList(
                    entities = networkCharacterList.map(NetworkCharacterListItem::asEntity),
                )
                emit(
                    networkCharacterList
                        .map(NetworkCharacterListItem::asEntity)
                        .map(CharacterListItemEntity::asExternalModel)
                )
            }
            // If offline and cached value is empty, emit empty list
            offlineList.isEmpty() -> {
                emit(emptyList())
            }
        }
    }
}