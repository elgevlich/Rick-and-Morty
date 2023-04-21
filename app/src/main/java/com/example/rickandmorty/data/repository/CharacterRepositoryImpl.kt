package com.example.rickandmorty.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.data.api.CharacterApi
import com.example.rickandmorty.data.pagingSource.CharacterPagingSource
import com.example.rickandmorty.domain.model.character.Character
import com.example.rickandmorty.domain.model.character.EpisodesResponse
import com.example.rickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class CharacterRepositoryImpl(
    private val service: CharacterApi,
) : CharacterRepository {


    override suspend fun getCharacters(
        name: String,
        status: String,
        gender: String
    ): Flow<PagingData<Character>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = { CharacterPagingSource(name, status, gender, service) }
    ).flow

    override suspend fun getCharacterEpisodes(id: Int): Character? {
        return service.getCharacter(id).body()
    }
}
