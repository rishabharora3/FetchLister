package com.project.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.model.data.CharacterListItem


@Entity(
    tableName = "character_list",
)
data class CharacterListItemEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val status: String,
    val species: String,
)

fun CharacterListItemEntity.asExternalModel() = CharacterListItem(
    id = id,
    name = name,
    status = status,
    species = species,
)