package com.project.network.model

import kotlinx.serialization.Serializable
import com.project.model.data.ListItem

/**
 * Network representation of [ListItem]
 */
@Serializable
data class NetworkListItem(
    val id: Int,
    val listId: Int,
    val name: String? = "",
)