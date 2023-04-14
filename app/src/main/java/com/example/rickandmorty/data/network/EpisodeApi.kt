package com.example.rickandmorty.data.network

import com.example.rickandmorty.data.model.EpisodeList
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodeApi {
	@GET("api/episode")
	suspend fun getEpisodes(@Query("page") page : Int): EpisodeList

	@GET("api/episode")
	suspend fun getEpisodesByName(@Query("name") name : String): EpisodeList

}