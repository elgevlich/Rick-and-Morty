package com.example.rickandmorty.data.api

import com.example.rickandmorty.domain.model.character.Character
import com.example.rickandmorty.domain.model.character.CharacterPagedResponse
import io.reactivex.Observable
import retrofit2.Response
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
	): Response<CharacterPagedResponse<Character>>

	@GET("api/character/{id}")
	fun getListOfCharactersForDetails(@Path("id") id: String): Observable<List<Character>>
}