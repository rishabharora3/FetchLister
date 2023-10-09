package com.project.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.model.data.CharacterDetail

@Entity(
    tableName = "character_detail",
)
data class CharacterDetailEntity(
    @PrimaryKey
    val id: String,
    val image: String,
    val characterName: String,
    val locationName: String,
    val locationType: String,
    val locationDimension: String,
    val locationNumberOfResidents: Int
)

fun CharacterDetailEntity.asExternalModel() = CharacterDetail(
    id = id,
    image = image,
    characterName = characterName,
    locationName = locationName,
    locationType = locationType,
    locationDimension = locationDimension,
    locationNumberOfResidents = locationNumberOfResidents
)