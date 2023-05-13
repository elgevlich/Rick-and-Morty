package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.api.LocationApi
import com.example.rickandmorty.data.database.dao.LocationDao
import com.example.rickandmorty.data.mappers.LocationMapper
import com.example.rickandmorty.domain.model.location.Location
import com.example.rickandmorty.domain.model.location.LocationResult
import com.example.rickandmorty.domain.repository.LocationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
	private val apiService: LocationApi,
	private val locationDao: LocationDao,
	private val mapper: LocationMapper
) : LocationRepository {

	override suspend fun getLocation(
		page: Int,
		name: String,
		type: String,
		dimension: String,
	): Location {
		var locationApi = apiService.getLocations(page, name, type, dimension)
		val listLocations = mapper.mapLocationResponseForLocation(locationApi)
		locationDao.insertLocation(mapper.mapListResultResponseForListDb(listLocations.results))
		return listLocations
	}

	override suspend fun insertLocation(list: List<LocationResult>) {
		locationDao.insertLocation(mapper.mapListResultResponseForListDb(list))
	}

	override fun getListLocationsDb(): List<LocationResult> {
		var listLocations = emptyList<LocationResult>()
		CoroutineScope(Dispatchers.IO).launch {
			listLocations = (locationDao.getAllLocation()).map {
				mapper.mapLocationResultDbForLocationResult(it)
			}
		}
		return listLocations
	}
}