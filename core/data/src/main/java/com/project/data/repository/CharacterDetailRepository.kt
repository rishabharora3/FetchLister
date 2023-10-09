package com.project.data.repository

import com.project.model.data.CharacterDetail
import kotlinx.coroutines.flow.Flow

interface CharacterDetailRepository {
    fun getCharacterDetail(characterId: String): Flow<CharacterDetail>
}