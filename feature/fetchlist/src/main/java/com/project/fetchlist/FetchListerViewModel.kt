package com.project.fetchlist

import androidx.lifecycle.ViewModel
import com.project.domain.GetFilteredAndSortedGroupedListItemsUseCase
import com.project.ui.ListFeedUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay


@HiltViewModel
class FetchListerViewModel @Inject constructor(
    getFilteredAndSortedGroupedListItemsUseCase: GetFilteredAndSortedGroupedListItemsUseCase
) : ViewModel() {

    val uiState: StateFlow<ListFeedUiState> =
        getFilteredAndSortedGroupedListItemsUseCase().map { items ->
            // show progress bar for 2 seconds
            delay(2000)

            if (items.isEmpty()) {
                ListFeedUiState.Empty
            } else {
                ListFeedUiState.Success(items)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ListFeedUiState.Loading,
        )
}