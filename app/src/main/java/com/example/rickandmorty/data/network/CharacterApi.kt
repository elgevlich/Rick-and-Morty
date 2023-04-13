package com.example.rickandmorty.data.network

import com.example.rickandmorty.data.model.CharacterPagedResponse
import com.example.rickandmorty.data.model.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface CharacterApi {

    @GET("api/character/")
    suspend fun getCharacters(@Query("page") page: Int): Response<CharacterPagedResponse<Character>>

    suspend fun getSingleCharacter(id: Int)

    suspend fun getMultipleCharacters(ids: List<Int>)

    suspend fun filterCharacters(
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String
    )

}