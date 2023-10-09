package com.project.data.repository

import com.project.model.data.CharacterListItem
import kotlinx.coroutines.flow.Flow

interface CharacterListRepository {
    /**
     * Gets the available CharacterList as a stream
     */
    fun getCharacterList(page: Int): Flow<List<CharacterListItem>>
}