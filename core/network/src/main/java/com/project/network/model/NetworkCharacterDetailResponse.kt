package com.project.network.model

import com.google.gson.annotations.SerializedName


data class NetworkCharacterDetailResponse(
    @SerializedName("data") var data: Data? = Data()
) {
    data class Data(
        @SerializedName("character") var networkCharacterDetail: NetworkCharacterDetail? = NetworkCharacterDetail(
            id = ""
        )
    )
}