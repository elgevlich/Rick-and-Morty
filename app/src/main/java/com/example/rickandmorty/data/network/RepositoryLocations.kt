package com.example.rickandmorty.data.network

import com.example.rickandmorty.data.model.LocationList


class RepositoryLocations {

	suspend fun getLocations(page: Int): LocationList {
		return RetrofitInstance.locationApi.getLocation(page)
	}

	suspend fun getLocationsByName(name: String): LocationList {
		return RetrofitInstance.locationApi.getLocationsByName(name)
	}
}
