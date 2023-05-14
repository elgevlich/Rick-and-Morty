package com.example.rickandmorty.data.api

import com.example.rickandmorty.data.api.response.character.CharacterResponse
import com.example.rickandmorty.domain.model.character.CharacterResult
import com.example.rickandmorty.domain.model.episode.EpisodeResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface CharacterApi {

    @GET("api/character/")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("name") name: String,
        @Query("status") status: String,
        @Query("gender") gender: String
    ): CharacterResponse

    @GET("api/character/{id}")
    fun getListOfCharactersForDetails(@Path("id") id: String): Observable<List<CharacterResult>>

    @GET("api/episode/{id}")
    fun getDetailEpisode(@Path("id") id: String): Observable<List<EpisodeResult>>
}