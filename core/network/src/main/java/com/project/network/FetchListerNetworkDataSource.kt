package com.project.network

import com.project.network.model.NetworkCharacterDetail
import com.project.network.model.NetworkCharacterListItem
import com.project.network.model.NetworkListItem

/**
 * Interface representing network calls to the Fetch Backend
 */
interface FetchListerNetworkDataSource {
    suspend fun getListItems(): List<NetworkListItem>
    suspend fun getCharacterList(page: Int): Result<List<NetworkCharacterListItem>>
    suspend fun getCharacterDetail(id: String): Result<NetworkCharacterDetail>
}
