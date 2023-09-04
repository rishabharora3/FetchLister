package com.project.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.project.network.FetchNetworkDataSource
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton
import com.project.network.model.NetworkListItem
import com.project.network.BuildConfig


/**
 * Retrofit API declaration for Fetch API
 */
private interface RetrofitFetchListerNetworkApi {
    @GET(value = "hiring.json")
    suspend fun getListItems(): NetworkResponse<List<NetworkListItem>>
}

private const val BASE_URL = BuildConfig.BACKEND_URL

/**
 * Wrapper for data provided from the [BASE_URL]
 */
@Serializable
private data class NetworkResponse<T>(
    val data: T,
)


/**
 * [Retrofit] backed [FetchNetworkDataSource]
 */
@Singleton
class RetrofitNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory,
) : FetchNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(RetrofitFetchListerNetworkApi::class.java)

    override suspend fun getListItems(): List<NetworkListItem> =
        networkApi.getListItems().data
}