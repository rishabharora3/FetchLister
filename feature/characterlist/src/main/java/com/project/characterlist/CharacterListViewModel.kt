package com.project.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.data.repository.CharacterListRepository
import com.project.model.data.CharacterListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class CharacterListViewModel @Inject constructor(
    characterListRepository: CharacterListRepository,
) : ViewModel() {

    val uiState: StateFlow<CharacterListUiState> =
        characterListRepository.getCharacterList(
            page = 1,
        ).map { items ->
            when {
                items.isEmpty() -> {
                    CharacterListUiState.Empty
                }

                else -> {
                    CharacterListUiState.Success(items)
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CharacterListUiState.Loading,
        )
}

/**
 * A sealed hierarchy describing the state of the feed of list items.
 */
sealed interface CharacterListUiState {
    /**
     * The feed is still loading.
     */
    object Loading : CharacterListUiState

    /**
     * The feed is loaded with the given list of list items.
     */
    data class Success(
        /**
         * The list of list items contained in this feed.
         */
        val items: List<CharacterListItem>,
    ) : CharacterListUiState

    object Empty : CharacterListUiState

}