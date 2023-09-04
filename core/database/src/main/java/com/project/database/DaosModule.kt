package com.project.database

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.project.database.dao.ListItemDao

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesListItemDao(
        database: FetchListerDatabase,
    ): ListItemDao = database.listItemDao()
}
