package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.domain.model.character.CharacterResult
import com.example.rickandmorty.domain.model.location.Location
import com.example.rickandmorty.domain.model.location.LocationResult
import io.reactivex.Observable

interface LocationRepository {

    suspend fun getLocation(page: Int, name: String, type: String, dimension: String): Location

    suspend fun insertLocation(list: List<LocationResult>)

    suspend fun getListLocationsDb(
        offset: Int,
        limit: Int,
        name: String,
        type: String,
        dimension: String
    ): List<LocationResult>

    fun getDetailCharacter(id: String): Observable<List<CharacterResult>>

    fun getDetailLocation(name: String): Observable<Location>

}