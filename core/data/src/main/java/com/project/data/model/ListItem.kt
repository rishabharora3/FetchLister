package com.project.data.model

import com.project.database.model.ListItemEntity
import com.project.network.model.NetworkListItem

fun NetworkListItem.asEntity() = ListItemEntity(
    id = id,
    listId = listId,
    name = name ?: "",
)
