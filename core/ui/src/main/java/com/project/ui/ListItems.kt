package com.project.ui

import com.project.model.data.ListItem


/**
 * A sealed hierarchy describing the state of the feed of list items.
 */
sealed interface ListFeedUiState {
    /**
     * The feed is still loading.
     */
    object Loading : ListFeedUiState

    /**
     * The feed is loaded with the given list of list items.
     */
    data class Success(
        /**
         * The list of list items contained in this feed.
         */
        val items: List<ListItem>,
    ) : ListFeedUiState

    object Empty : ListFeedUiState
}