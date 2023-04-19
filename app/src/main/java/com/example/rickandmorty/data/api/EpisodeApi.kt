package com.example.rickandmorty.data.api

import com.example.rickandmorty.domain.model.episode.Episode
import com.example.rickandmorty.domain.model.episode.EpisodePagedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodeApi {

	@GET("api/episode/")
	suspend fun getEpisodes(
		@Query("page") page: Int,
		@Query("name") name: String,
		@Query("episode") episode: String,
	): Response<EpisodePagedResponse<Episode>>

}