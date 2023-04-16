package com.example.rickandmorty.data.api

import com.example.rickandmorty.domain.model.episode.EpisodeList
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodeApi {
	@GET("api/episode")
	suspend fun getEpisodes(@Query("page") page : Int): EpisodeList

	@GET("api/episode")
	suspend fun getEpisodesByName(@Query("name") name : String): EpisodeList

}