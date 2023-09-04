package com.project.network.di


import com.project.network.FetchListerNetworkDataSource
import com.project.network.retrofit.RetrofitListerNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FlavoredNetworkModule {

    @Binds
    fun RetrofitListerNetwork.binds(): FetchListerNetworkDataSource
}