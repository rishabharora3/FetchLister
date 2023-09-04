package com.project.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.project.database.util.InstantConverter
import com.project.database.dao.ListItemDao
import com.project.database.model.ListItemEntity

@Database(
    entities = [
        ListItemEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(
    InstantConverter::class,
)
abstract class FetchListerDatabase : RoomDatabase() {
    abstract fun listItemDao(): ListItemDao
}
