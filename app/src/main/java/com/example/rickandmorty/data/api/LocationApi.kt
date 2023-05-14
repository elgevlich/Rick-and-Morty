package com.example.rickandmorty.data.api


import com.example.rickandmorty.data.api.response.location.LocationResponse
import com.example.rickandmorty.domain.model.character.CharacterResult
import com.example.rickandmorty.domain.model.location.Location
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
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
    ): Observable<Location>

    @GET("character/{id}")
    fun getDetailCharacter(@Path("id") id: String): Observable<List<CharacterResult>>

    @GET("location/")
    fun getDetailLocation(@Query("name") name: String): Observable<Location>

}