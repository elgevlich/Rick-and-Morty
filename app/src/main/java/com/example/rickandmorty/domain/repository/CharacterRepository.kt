package com.example.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.example.rickandmorty.domain.model.character.Character
import com.example.rickandmorty.domain.model.character.EpisodesResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Query

interface CharacterRepository {

    suspend fun getCharacters(
        name: String,
        status: String,
        gender: String
    ): Flow<PagingData<Character>>

    suspend fun getCharacterEpisodes(id: Int): Character?


}