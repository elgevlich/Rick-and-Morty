package com.example.rickandmorty.data.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.data.model.CharacterPagedResponse
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryImpl(
    private val service: CharacterApi,
) : CharacterRepository {

    override suspend fun getCharacters(): Flow<PagingData<Character>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = { CharacterPagingSource(service) }
    ).flow

    override suspend fun getSingleCharacter(id: Int) = service.getSingleCharacter(id)

    override suspend fun getMultipleCharacters(ids: List<Int>) = service.getMultipleCharacters(ids)

    override suspend fun filterCharacters(
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String
    ) = service.filterCharacters(name, status, species, type, gender)

}