package com.project.data.di

import com.project.data.repository.ListItemRepository
import com.project.data.repository.OfflineFirstListItemRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.project.data.util.ConnectivityManagerNetworkMonitor
import com.project.data.util.NetworkMonitor

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsListItemRepository(
        listItemRepository: OfflineFirstListItemRepository,
    ): ListItemRepository

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor
}