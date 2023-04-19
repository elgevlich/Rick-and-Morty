package com.example.rickandmorty.data.pagingSource

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.api.EpisodeApi
import com.example.rickandmorty.domain.model.episode.Episode

class EpisodePagingSource(
	private val name: String,
	private val episode: String,
	private val service: EpisodeApi
) :
	PagingSource<Int, Episode>() {
	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Episode> {
		val pageNumber = params.key ?: 1

		return try {
			val response = service.getEpisodes(pageNumber, name, episode)
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

	override fun getRefreshKey(state: PagingState<Int, Episode>): Int = 1
}