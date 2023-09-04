package com.project.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.project.database.model.ListItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ListItemDao {

    @Upsert
    suspend fun upsertListItem(entities: List<ListItemEntity>)

    @Query(value = "SELECT * FROM list_item")
    fun getListItemEntity(): Flow<List<ListItemEntity>>
}