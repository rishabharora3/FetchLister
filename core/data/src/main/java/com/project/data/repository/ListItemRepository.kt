package com.project.data.repository

import kotlinx.coroutines.flow.Flow
import com.project.model.data.ListItem

interface ListItemRepository {
    /**
     * Gets the available ListItem as a stream
     */
    fun getListItems(): Flow<List<ListItem>>
}