package com.example.rickandmorty.data.network

import com.example.rickandmorty.data.model.PagedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.rickandmorty.data.model.Character
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface CharacterApi {

    @GET("api/character")
    suspend fun getAllCharacters(@Query("page") page: Int): Response<PagedResponse<Character>>


    companion object {

        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api: CharacterApi by lazy {
            retrofit.create(CharacterApi::class.java)
        }
    }
}