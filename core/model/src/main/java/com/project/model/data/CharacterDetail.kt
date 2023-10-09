package com.project.model.data

/**
 * External data layer representation of a CharacterDetailEntity
 */
data class CharacterDetail(
    val id: String,
    val image: String,
    val characterName: String,
    val locationName: String,
    val locationType: String,
    val locationDimension: String,
    val locationNumberOfResidents: Int
)
