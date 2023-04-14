package com.example.rickandmorty.data.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.data.model.CharacterPagedResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CharacterRepositoryImpl: CharacterRepository {

    private val apiService = RetrofitInstance.Api

    override suspend fun getCharacters(
        page: Int,
        name: String,
        status: String,
        gender: String
    ): Character {
        val character = apiService.getCharacters(page, status, gender)
        return Response
    }


}