package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.domain.model.location.Location
import com.example.rickandmorty.domain.model.location.LocationResult

interface LocationRepository {

	suspend fun getLocation(page: Int, name: String, type: String, dimension: String): Location

	suspend fun insertLocation(list: List<LocationResult>)

	fun getListLocationsDb(): List<LocationResult>

}