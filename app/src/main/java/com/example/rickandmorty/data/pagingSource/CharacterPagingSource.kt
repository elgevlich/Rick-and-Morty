package com.example.rickandmorty.data.pagingSource

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.example.rickandmorty.domain.model.character.CharacterResult
import com.example.rickandmorty.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterPagingSource @Inject constructor(
	private val repository: CharacterRepository,
	private val application: Application,
	private val name: String,
	private val status: String,
	private val gender: String,
) : PagingSource<Int, CharacterResult>() {


	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResult> {
		return try {
			val page = params.key ?: 1
			var nextKey: Int? = 0
			val responseData = arrayListOf<CharacterResult>()
			nextKey = if (hasConnected(application.applicationContext)) {
				val response = repository.getCharacter(page, name, status, gender)
				responseData.addAll(response.result)
				if (response.info.next == null && responseData.isNotEmpty()) null else page + 1
			} else {
				val listCharacters = repository.getListCharactersDb()
				responseData.addAll(listCharacters)
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

	override fun getRefreshKey(state: PagingState<Int, CharacterResult>): Int? {
		return null
	}

	private fun hasConnected(context: Context): Boolean {
		val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
		val network = manager.activeNetworkInfo
		return network != null && network.isConnected
	}
}

