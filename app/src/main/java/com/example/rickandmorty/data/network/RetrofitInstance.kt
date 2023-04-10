package com.example.rickandmorty.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RetrofitInstance {

	private val retrofit by lazy {
		Retrofit.Builder()
			.baseUrl("https://rickandmortyapi.com/")
			.addConverterFactory(GsonConverterFactory.create())
			.build()
	}

	val api: ApiService by lazy {
		retrofit.create(ApiService::class.java)
	}
}