package com.project.network.model

import com.google.gson.annotations.SerializedName

data class NetworkCharacterListResponse(
    @SerializedName("data") var data: Data? = Data()
) {
    data class Data(
        @SerializedName("characters") var characters: Characters? = Characters()
    )

    data class Characters(
        @SerializedName("info") var info: Info? = Info(),
        @SerializedName("results") var networkCharacterListItems: ArrayList<NetworkCharacterListItem> = arrayListOf()
    ) {
        data class Info(
            @SerializedName("count") var count: Int? = null,
            @SerializedName("pages") var pages: Int? = null,
            @SerializedName("next") var next: Int? = null,
            @SerializedName("prev") var prev: String? = null
        )
    }
}