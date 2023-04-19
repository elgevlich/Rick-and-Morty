package com.example.rickandmorty.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.data.api.LocationApi
import com.example.rickandmorty.data.pagingSource.LocationPagingSource
import com.example.rickandmorty.domain.model.location.Location
import com.example.rickandmorty.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow

class LocationRepositoryImpl(
	private val service: LocationApi,
) : LocationRepository {


	override suspend fun getLocations(
		name: String,
		type: String,
		dimension: String,
	): Flow<PagingData<Location>> = Pager(
		config = PagingConfig(pageSize = 20, prefetchDistance = 2),
		pagingSourceFactory = { LocationPagingSource(name, type, dimension, service) }
	).flow


}