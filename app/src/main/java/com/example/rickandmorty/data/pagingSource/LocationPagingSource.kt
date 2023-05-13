package com.example.rickandmorty.data.pagingSource

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.example.rickandmorty.domain.model.location.LocationResult
import com.example.rickandmorty.domain.repository.LocationRepository
import javax.inject.Inject

class LocationPagingSource @Inject constructor(
	private val repository: LocationRepository,
	private val application: Application,
	private val name: String,
	private val type: String,
	private val dimension: String
) : PagingSource<Int, LocationResult>() {

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationResult> {
		return try {
			val page = params.key ?: 1
			var nextKey: Int? = 0
			val responseData = arrayListOf<LocationResult>()
			nextKey = if (hasConnected(application.applicationContext)) {
				val response = repository.getLocation(page, name, type, dimension)
				responseData.addAll(response.results)
				if (response.info.next == null && responseData.isNotEmpty()) null else page + 1
			} else {
				val listLocations = repository.getListLocationsDb()
				responseData.addAll(listLocations)
				if (responseData.isNotEmpty()) null else page + 1
			}
			val prevKey = if (page == 1) null else page - 1
			return LoadResult.Page(
				data = responseData,
				prevKey = prevKey,
				nextKey = nextKey
			)
		} catch (e: Exception) {
			return LoadResult.Error(e)
		} catch (e: HttpException) {
			LoadResult.Page(
				data = emptyList(),
				prevKey = null,
				nextKey = null
			)
		}
	}

	override fun getRefreshKey(state: PagingState<Int, LocationResult>): Int? {
		return null
	}

	private fun hasConnected(context: Context): Boolean {
		val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
		val network = manager.activeNetworkInfo
		return network != null && network.isConnected
	}
}