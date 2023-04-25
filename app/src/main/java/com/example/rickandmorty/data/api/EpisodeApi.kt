package com.example.rickandmorty.data.api

import com.example.rickandmorty.domain.model.episode.Episode
import com.example.rickandmorty.domain.model.episode.EpisodePagedResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodeApi {

	@GET("api/episode/")
	suspend fun getEpisodes(
		@Query("page") page: Int,
		@Query("name") name: String,
		@Query("episode") episode: String,
	): Response<EpisodePagedResponse<Episode>>

	@GET("api/episode/{id}")
	fun getListOfEpisodesForDetails(@Path("id") id: String): Observable<List<Episode>>

	@GET("api/episode/{id}")
	fun getEpisodeId(
		@Path("id") id: Int
	): Episode

}