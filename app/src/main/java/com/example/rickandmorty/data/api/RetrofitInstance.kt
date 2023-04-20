package com.example.rickandmorty.data.api


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {


	private val retrofit by lazy {
		val logging = HttpLoggingInterceptor()
		logging.setLevel(HttpLoggingInterceptor.Level.BODY)
		val client = OkHttpClient.Builder()
			.addInterceptor(logging)
			.build()
		Retrofit.Builder()
			.baseUrl("https://rickandmortyapi.com/")
			.addConverterFactory(GsonConverterFactory.create())
			.client(client)
			.build()
	}

   val episodeApi: EpisodeApi by lazy {
		retrofit.create(EpisodeApi::class.java)
	}

	val characterApi: CharacterApi by lazy {
		retrofit.create(CharacterApi::class.java)
	}

	val locationApi: LocationApi by lazy {
		retrofit.create(LocationApi::class.java)
	}

}