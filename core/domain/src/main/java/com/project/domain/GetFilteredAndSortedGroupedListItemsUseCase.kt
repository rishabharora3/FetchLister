package com.project.domain

import com.project.data.repository.ListItemRepository
import com.project.model.data.ListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSortedListItemsUseCase @Inject constructor(
    private val listItemRepository: ListItemRepository
) {
    operator fun invoke(): Flow<List<ListItem>> {
        return listItemRepository.getListItems()
            .map { listItems ->
                // Filter out items with blank or null names
                val filteredItems = listItems.filter { it.name.isNotBlank() }

                // Sort the filtered items by "listId" and then by "name"
                val sortedItems = filteredItems.sortedWith(compareBy({ it.listId }, { it.name }))

                // Group the sorted items by "listId"
                sortedItems.groupBy { it.listId }
                    .values
                    .flatten()
            }
    }
}