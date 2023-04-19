package com.example.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.example.rickandmorty.domain.model.location.Location
import kotlinx.coroutines.flow.Flow


interface LocationRepository {

	suspend fun getLocations(name: String, type: String, dimension: String): Flow<PagingData<Location>>

}
