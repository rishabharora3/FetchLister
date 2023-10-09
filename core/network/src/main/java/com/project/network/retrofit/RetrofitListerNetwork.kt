package com.project.network.retrofit

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.project.network.BuildConfig
import com.project.network.FetchListerNetworkDataSource
import com.project.network.gql.CHARACTER_ID
import com.project.network.gql.CharacterDetailQuery
import com.project.network.gql.CharacterListQuery
import com.project.network.gql.PAGE
import com.project.network.model.GraphQLRequest
import com.project.network.model.NetworkCharacterDetail
import com.project.network.model.NetworkCharacterDetailResponse
import com.project.network.model.NetworkCharacterListItem
import com.project.network.model.NetworkCharacterListResponse
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Retrofit API declaration for Rick and Morty API
 */
private interface RetrofitFetchListerNetworkApi {
    @POST(GRAPHQL_PATH)
    suspend fun getCharacterList(
        @Body
        request: GraphQLRequest,
    ): NetworkCharacterListResponse

    @POST(GRAPHQL_PATH)
    suspend fun getCharacterDetail(
        @Body
        request: GraphQLRequest,
    ): NetworkCharacterDetailResponse

}

private const val BASE_URL = BuildConfig.BACKEND_URL
private const val GRAPHQL_PATH = "graphql"

/**
 * Configures and returns a [GraphQLRequest] to be used for [RetrofitFetchListerNetworkApi.getCharacterList]
 */
internal fun getCharacterListGQLRequest(
    page: Int
) = GraphQLRequest().apply {
    query = CharacterListQuery
    setVariable(PAGE, page)
}

/**
 * Configures and returns a [GraphQLRequest] to be used for [RetrofitFetchListerNetworkApi.getCharacterList]
 */
internal fun getCharacterDetailGQLRequest(
    characterId: Int
) = GraphQLRequest().apply {
    query = CharacterDetailQuery
    setVariable(CHARACTER_ID, characterId)
}


/**
 * Wrapper for data provided from the [BASE_URL]
 */
@Serializable
private data class NetworkResponse<T>(
    val data: T,
)


/**
 * [Retrofit] backed [FetchListerNetworkDataSource]
 */
@Singleton
class RetrofitListerNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory,
) : FetchListerNetworkDataSource {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Set log level as needed (BASIC, HEADERS, BODY)
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val gson: Gson = getGsonBuilder()
    private val gsonConverterFactory: Converter.Factory = GsonConverterFactory.create(gson)

    private val networkApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(gsonConverterFactory)
        .client(client)
        .build()
        .create(RetrofitFetchListerNetworkApi::class.java)

    private fun getGsonBuilder(): Gson {
        return GsonBuilder()
            .registerTypeHierarchyAdapter(GraphQLRequest::class.java, GraphQLRequestTypeAdapter())
            .create()
    }

    override suspend fun getCharacterList(
        page: Int,
    ): Result<List<NetworkCharacterListItem>> {
        return runCatching {
            networkApi.getCharacterList(
                getCharacterListGQLRequest(page)
            ).data
                ?.characters
                ?.networkCharacterListItems
                .ifNullOrEmpty(
                    onNull = {
                        Log.d(CLASS_TAG, "Character List was null")
                        emptyList()
                    },
                    onEmpty = {
                        Log.d(CLASS_TAG, "Character List was empty")
                        emptyList()
                    }
                )
        }.onFailure {
            Log.d(CLASS_TAG, "Character List failed to load: $it")
        }
    }

    override suspend fun getCharacterDetail(id: String): Result<NetworkCharacterDetail> {
        return runCatching {
            networkApi.getCharacterDetail(
                getCharacterDetailGQLRequest(id.toInt())
            ).data
                ?.networkCharacterDetail ?: NetworkCharacterDetail(id = "")
        }.onFailure {
            Log.d(CLASS_TAG, "Character Detail failed to load: $it")
        }
    }


    /**
     * Modification on [runCatching] from the Kotlin [Result] API.
     *
     * This alternative catches [Exception]s instead of all [Throwable]s.
     */
    private inline fun <T, R> T.runCatching(block: T.() -> R): Result<R> {
        return try {
            Result.success(block())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Return [this] list if it is not `null` or empty
     *
     * @param onNull result for when [this] is null
     * @param onEmpty result for when [this] is empty
     */
    private fun <T> List<T>?.ifNullOrEmpty(
        onNull: () -> List<T>,
        onEmpty: () -> List<T>,
    ) = this?.ifEmpty(onEmpty) ?: onNull()

    companion object {
        private val CLASS_TAG = RetrofitListerNetwork::class.java.simpleName
    }
}
