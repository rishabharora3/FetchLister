package com.project.network

import com.project.network.model.NetworkCharacterDetail
import com.project.network.model.NetworkCharacterListItem

/**
 * Interface representing network calls to the Fetch Backend
 */
interface FetchListerNetworkDataSource {
    suspend fun getCharacterList(page: Int): Result<List<NetworkCharacterListItem>>
    suspend fun getCharacterDetail(id: String): Result<NetworkCharacterDetail>
}
