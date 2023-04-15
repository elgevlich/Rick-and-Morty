package com.example.rickandmorty.data.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.data.model.CharacterPagedResponse
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


}
