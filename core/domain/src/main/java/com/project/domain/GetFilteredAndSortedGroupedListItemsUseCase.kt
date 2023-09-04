package com.project.domain

import com.project.data.repository.ListItemRepository
import com.project.model.data.ListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFilteredAndSortedGroupedListItemsUseCase @Inject constructor(
    private val listItemRepository: ListItemRepository
) {
    operator fun invoke(): Flow<List<ListItem>> {
        return listItemRepository.getListItems()
            .map { listItems ->
                val filteredItems = listItems.filter { it.name.isNotBlank() }
                val sortedItems = filteredItems.sortedWith(compareBy({ it.listId }, { it.name }))
                sortedItems.groupBy { it.listId }
                    .values
                    .flatten()
            }
    }
}