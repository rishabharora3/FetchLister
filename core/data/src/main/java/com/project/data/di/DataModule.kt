package com.project.data.di

import com.project.data.repository.CharacterDetailRepository
import com.project.data.repository.CharacterListRepository
import com.project.data.repository.OfflineFirstCharacterDetailRepository
import com.project.data.repository.OfflineFirstCharacterListRepository
import com.project.data.util.ConnectivityManagerNetworkMonitor
import com.project.data.util.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsCharacterListRepository(
        characterListRepository: OfflineFirstCharacterListRepository,
    ): CharacterListRepository

    @Binds
    fun bindsCharacterDetailRepository(
        characterDetailRepository: OfflineFirstCharacterDetailRepository,
    ): CharacterDetailRepository

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor
}