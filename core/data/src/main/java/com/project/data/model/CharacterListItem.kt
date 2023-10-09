package com.project.data.model

import com.project.database.model.CharacterListItemEntity
import com.project.network.model.NetworkCharacterListItem

fun NetworkCharacterListItem.asEntity() = CharacterListItemEntity(
    id = id,
    name = name ?: "",
    status = status ?: "",
    species = species ?: "",
)
