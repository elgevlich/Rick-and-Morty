package com.example.rickandmorty.data.pagingSource

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.api.LocationApi
import com.example.rickandmorty.domain.model.location.Location

class LocationPagingSource(
	private val name: String,
	private val type: String,
	private val dimension: String,
	private val service: LocationApi
) :
	PagingSource<Int, Location>() {

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Location> {
		val pageNumber = params.key ?: 1

		return try {
			val response = service.getLocations(pageNumber, name, type, dimension)
			val pagedResponse = response.body()
			val data = pagedResponse?.results

			var nextPageNumber: Int? = null
			if (pagedResponse?.pageInfo?.next != null) {
				val uri = Uri.parse(pagedResponse.pageInfo.next)
				val nextPageQuery = uri.getQueryParameter("page")
				nextPageNumber = nextPageQuery?.toInt()
			}

			LoadResult.Page(
				data = data.orEmpty(),
				prevKey = null,
				nextKey = nextPageNumber
			)
		} catch (e: Exception) {
			LoadResult.Error(e)
		}
	}

	override fun getRefreshKey(state: PagingState<Int, Location>): Int = 1
}