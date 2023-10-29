package com.project.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.project.database.model.CharacterDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDetailDao {
    @Upsert
    suspend fun upsertCharacterDetail(characterDetailEntity: CharacterDetailEntity)

    @Query(value = "SELECT * FROM character_detail WHERE id = :characterId")
    fun getCharacterDetail(characterId: String): Flow<CharacterDetailEntity?>
}