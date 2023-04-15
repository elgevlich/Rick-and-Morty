package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.data.api.RetrofitInstance
import com.example.rickandmorty.domain.model.location.LocationList


class RepositoryLocations {

	suspend fun getLocations(page: Int): LocationList {
		return RetrofitInstance.locationApi.getLocation(page)
	}

	suspend fun getLocationsByName(name: String): LocationList {
		return RetrofitInstance.locationApi.getLocationsByName(name)
	}
}
