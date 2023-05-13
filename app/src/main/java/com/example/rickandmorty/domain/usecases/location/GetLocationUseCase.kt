package com.example.rickandmorty.domain.usecases.location

import android.app.Application
import com.example.rickandmorty.data.pagingSource.LocationPagingSource
import com.example.rickandmorty.data.repository.LocationRepositoryImpl
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
	private val repository: LocationRepositoryImpl,
	private val application: Application
) {

	fun getLocation(name: String, type: String, dimension: String): LocationPagingSource {
		return LocationPagingSource(repository, application, name, type, dimension)
	}
}