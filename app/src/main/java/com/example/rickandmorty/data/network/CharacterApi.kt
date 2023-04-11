package com.example.rickandmorty.data.network

import com.example.rickandmorty.data.model.PagedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.rickandmorty.data.model.Character


interface CharacterApi {

    @GET("character/")
    suspend fun getAllCharacters(@Query("page") page: Int): Response<PagedResponse<Character>>

}