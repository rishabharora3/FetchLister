package com.project.characterdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.characterdetail.navigation.CharacterDetailArgs
import com.project.data.repository.OfflineFirstCharacterDetailRepository
import com.project.model.data.CharacterDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    offlineFirstCharacterDetailRepository: OfflineFirstCharacterDetailRepository,
) : ViewModel() {
    private val characterDetailArgs: CharacterDetailArgs = CharacterDetailArgs(savedStateHandle)

    val uiState: StateFlow<CharacterDetailUiState> =
        offlineFirstCharacterDetailRepository.getCharacterDetail(
            characterId = characterDetailArgs.characterId,
        ).map { characterDetail ->
            when {
                characterDetail.id.isEmpty() -> {
                    CharacterDetailUiState.Error
                }

                else -> {
                    CharacterDetailUiState.Success(characterDetail)
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CharacterDetailUiState.Loading,
        )
}

sealed interface CharacterDetailUiState {
    data class Success(val characterDetail: CharacterDetail) : CharacterDetailUiState
    object Error : CharacterDetailUiState
    object Loading : CharacterDetailUiState
}