package com.project.network

import com.project.network.model.NetworkListItem

/**
 * Interface representing network calls to the Fetch Backend
 */
interface FetchListerNetworkDataSource {
    suspend fun getListItems(): List<NetworkListItem>
}
