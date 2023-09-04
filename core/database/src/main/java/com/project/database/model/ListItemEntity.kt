package com.project.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.model.data.ListItem

@Entity(
    tableName = "list_item",
)
data class ListItemEntity(
    @PrimaryKey
    val id: Int,
    val listId: Int,
    @ColumnInfo(defaultValue = "")
    val name: String,
)

fun ListItemEntity.asExternalModel() = ListItem(
    id = id,
    listId = listId,
    name = name,
)
