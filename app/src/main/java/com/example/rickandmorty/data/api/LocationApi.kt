package com.example.rickandmorty.data.api

import com.example.rickandmorty.domain.model.location.LocationList
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {

	@GET("api/location")
	suspend fun getLocation(@Query("page") page : Int): LocationList

	@GET("api/location")
	suspend fun getLocationsByName(@Query("name") name : String): LocationList
}