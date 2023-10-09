package com.project.database

import com.project.database.dao.CharacterDetailDao
import com.project.database.dao.CharacterListItemDao
import com.project.database.dao.ListItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun providesListItemDao(
        database: FetchListerDatabase,
    ): ListItemDao = database.listItemDao()

    @Provides
    fun providesCharacterListItemDao(
        database: FetchListerDatabase,
    ): CharacterListItemDao = database.characterListItemDao()

    @Provides
    fun providesCharacterDetailDao(
        database: FetchListerDatabase,
    ): CharacterDetailDao = database.characterDetailDao()
}
