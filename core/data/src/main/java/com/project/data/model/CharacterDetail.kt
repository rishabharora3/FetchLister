package com.project.data.model

import com.project.database.model.CharacterDetailEntity
import com.project.network.model.NetworkCharacterDetail


fun NetworkCharacterDetail.asEntity() = CharacterDetailEntity(
    id = id,
    characterName = name ?: "",
    image = image ?: "",
    locationName = location?.name ?: "",
    locationType = location?.type ?: "",
    locationDimension = location?.dimension ?: "",
    locationNumberOfResidents = location?.residents?.size ?: 0
)
