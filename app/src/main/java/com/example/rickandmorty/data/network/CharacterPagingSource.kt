package com.example.rickandmorty.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.data.model.CharacterPagedResponse
import retrofit2.Response

class CharacterPagingSource( private val status: ApiService,
    private val gender: String) :
    PagingSource<Int, Character>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val repository = CharacterRepositoryImpl()
            val page = params.key ?: 1
            val resultDate = arrayListOf<Character>()
            resultDate.addAll((repository.getCharacter(page, status,gender)).results)

            LoadResult.Page(
                data = resultDate,
                prevKey = if (page == 1) null else -1,
                nextKey = page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int = 1
}


