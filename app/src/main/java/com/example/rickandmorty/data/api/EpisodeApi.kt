package com.example.rickandmorty.data.api

import com.example.rickandmorty.domain.model.episode.Episode
import com.example.rickandmorty.domain.model.episode.EpisodeList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodeApi {
    @GET("api/episode")
    suspend fun getEpisodes(@Query("page") page: Int): EpisodeList

    @GET("api/episode")
    suspend fun getEpisodesByName(@Query("name") name: String): EpisodeList

    //PATH?! instead of @query
    @GET("api/episode")
    suspend fun getEpisodesByIds(@Query("id") ids: String): Response<List<Episode>>

}