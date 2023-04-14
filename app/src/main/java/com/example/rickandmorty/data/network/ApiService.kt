package com.example.rickandmorty.data.network

import com.example.rickandmorty.data.model.CharacterPagedResponse
import com.example.rickandmorty.data.model.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

	@GET("api/character/")
	suspend fun getCharacters(
		@Query("page") page: Int,
		@Query("status") status: String,
		@Query("gender") gender: String
	): Response<CharacterPagedResponse<Character>>


}