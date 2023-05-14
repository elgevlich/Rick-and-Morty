package com.example.rickandmorty.data.api

import com.example.rickandmorty.data.api.response.episode.EpisodeResponse
import com.example.rickandmorty.domain.model.character.CharacterResult
import com.example.rickandmorty.domain.model.episode.EpisodeResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodeApi {

    @GET("api/episode/")
    suspend fun getEpisodes(
        @Query("page") page: Int,
        @Query("name") name: String,
        @Query("episode") episode: String,
    ): EpisodeResponse

    @GET("api/episode/{id}")
    fun getListOfEpisodesForDetails(@Path("id") id: String): Observable<List<EpisodeResult>>

    @GET("character/{id}")
    fun getDetailCharacter(@Path("id") id: String): Observable<List<CharacterResult>>

}