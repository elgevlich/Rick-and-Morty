package com.example.rickandmorty.data.network

import com.example.rickandmorty.data.model.CharacterListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

	@GET("api/character")
	suspend fun load(@Query("page") page: Int): Response<CharacterListResponse>

}