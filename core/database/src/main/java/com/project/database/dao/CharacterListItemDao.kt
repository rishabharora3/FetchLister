package com.project.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.project.database.model.CharacterListItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterListItemDao {
    @Upsert
    suspend fun upsertCharacterList(entities: List<CharacterListItemEntity>)

    @Query(value = "SELECT * FROM character_list")
    fun getCharacterList(): Flow<List<CharacterListItemEntity>>
}