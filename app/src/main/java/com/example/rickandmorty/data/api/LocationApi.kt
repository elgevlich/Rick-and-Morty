package com.example.rickandmorty.data.api


import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {

	@GET("api/location/")
	suspend fun getLocations(
		@Query("page") page: Int,
		@Query("name") name: String,
		@Query("type") type: String,
		@Query("dimension") dimension: String,
	): LocationResponse

	@GET("api/location/")
	fun getLocation(
		@Query("name") name: String
	): Observable<LocationResponse>

}