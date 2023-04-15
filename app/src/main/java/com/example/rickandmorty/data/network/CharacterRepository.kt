package com.example.rickandmorty.data.network

import androidx.paging.PagingData
import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.data.model.CharacterPagedResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CharacterRepository {

    suspend fun getCharacters(name: String, status: String, gender: String): Flow<PagingData<Character>>

}