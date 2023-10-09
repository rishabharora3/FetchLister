package com.project.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.project.database.dao.CharacterDetailDao
import com.project.database.dao.CharacterListItemDao
import com.project.database.model.CharacterDetailEntity
import com.project.database.model.CharacterListItemEntity
import com.project.database.util.InstantConverter

@Database(
    entities = [
        CharacterListItemEntity::class,
        CharacterDetailEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(
    InstantConverter::class,
)
abstract class FetchListerDatabase : RoomDatabase() {
    abstract fun characterListItemDao(): CharacterListItemDao

    abstract fun characterDetailDao(): CharacterDetailDao
}
