package com.project.data.repository

import com.project.common.network.Dispatcher
import com.project.common.network.FetchListerDispatchers.IO
import com.project.common.network.di.ApplicationScope
import com.project.data.model.asEntity
import com.project.data.util.NetworkMonitor
import com.project.database.dao.ListItemDao
import com.project.database.model.ListItemEntity
import com.project.database.model.asExternalModel
import com.project.model.data.ListItem
import com.project.network.FetchListerNetworkDataSource
import com.project.network.model.NetworkListItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


class OfflineFirstListItemRepository @Inject constructor(
    private val listItemDao: ListItemDao,
    private val network: FetchListerNetworkDataSource,
    private val networkMonitor: NetworkMonitor,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    @ApplicationScope private val scope: CoroutineScope,
) : ListItemRepository {
    override fun getListItems(): Flow<List<ListItem>> {
        CoroutineScope(scope.coroutineContext + ioDispatcher).launch {
            syncWithRemote()
        }
        return listItemDao.getListItemEntity().map {
            it.map(ListItemEntity::asExternalModel)
        }
    }

    private suspend fun syncWithRemote() {
        if (networkMonitor.isOnline.first()) {
            val networkTopics = network.getListItems()
            listItemDao.upsertListItem(
                entities = networkTopics.map(NetworkListItem::asEntity),
            )
        }
    }
}