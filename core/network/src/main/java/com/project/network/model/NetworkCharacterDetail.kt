package com.project.network.model

import com.google.gson.annotations.SerializedName

data class NetworkCharacterDetail(
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("location") var location: Location? = Location()
) {
    data class Location(
        @SerializedName("name") var name: String? = null,
        @SerializedName("type") var type: String? = null,
        @SerializedName("dimension") var dimension: String? = null,
        @SerializedName("residents") var residents: ArrayList<Residents> = arrayListOf()
    ) {
        data class Residents(
            @SerializedName("id") var id: String? = null
        )
    }
}