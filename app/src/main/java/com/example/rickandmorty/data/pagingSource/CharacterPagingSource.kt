package com.example.rickandmorty.data.pagingSource

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.domain.model.character.CharacterResult
import com.example.rickandmorty.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterPagingSource @Inject constructor(
    private val repository: CharacterRepository,
    private val application: Application,
    private val name: String,
    private val status: String,
    private val gender: String
) : PagingSource<Int, CharacterResult>() {
    companion object {
        private const val START_PAGE = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResult> {
        try {
            var nextKey: Int? = 0
            val page = params.key ?: START_PAGE
            val responseData = if (hasConnected(application.applicationContext)) {
                val response = repository.getCharacter(page, name, gender, status)
                nextKey = if (response.info.next == null) null else page + 1
                response.result
            } else {
                val listCharacters = repository.getListCharacters(
                    (page - 1) * params.loadSize,
                    params.loadSize,
                    name,
                    gender,
                    status
                )
                nextKey = if (listCharacters.isNotEmpty()) page + 1 else null
                listCharacters
            }
            val prevKey = if (page == START_PAGE) null else page - 1
            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterResult>): Int? = null

    private fun hasConnected(context: Context): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = manager.activeNetworkInfo
        return network != null && network.isConnected
    }
}

