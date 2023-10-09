package com.project.network.model

import com.google.gson.annotations.SerializedName

data class NetworkCharacterListItem(
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("species") var species: String? = null
)