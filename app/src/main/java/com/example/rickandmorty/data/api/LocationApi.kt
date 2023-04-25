package com.example.rickandmorty.data.api

import com.example.rickandmorty.domain.model.location.Location
import com.example.rickandmorty.domain.model.location.LocationPagedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {

	@GET("api/location/")
	suspend fun getLocations(
		@Query("page") page: Int,
		@Query("name") name: String,
		@Query("type") type: String,
		@Query("dimension") dimension: String,
	): Response<LocationPagedResponse<Location>>

}