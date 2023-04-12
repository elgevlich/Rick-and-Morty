package com.example.rickandmorty.data.network

import com.example.rickandmorty.data.model.CharacterList
import retrofit2.http.GET
import retrofit2.http.Query


interface CharacterApi {

    @GET("api/character")
    suspend fun getCharacters(@Query("page") page : Int): CharacterList

    @GET("api/character")
    suspend fun getCharactersByName(@Query("name") name : String): CharacterList

    @GET("api/character")
    suspend fun getCharactersByStatusAndGender(@Query("status") status : String,
                                               @Query("gender") gender : String,
                                               @Query("page") page : Int): CharacterList

    @GET("api/character")
    suspend fun getCharactersByStatus(@Query("status") status : String, @Query("page") page : Int): CharacterList

    @GET("api/character")
    suspend fun getCharactersByGender( @Query("gender") gender : String, @Query("page") page : Int): CharacterList
}