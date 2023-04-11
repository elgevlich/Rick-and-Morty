package com.example.rickandmorty.data.network


import androidx.paging.PagingSource
import com.bumptech.glide.load.HttpException
import com.example.rickandmorty.data.Repository.Companion.DEFAULT_PAGE_INDEX
import java.io.IOException
import com.example.rickandmorty.data.model.Character


class CharactersPagingSource(private val api: ApiService) :
	PagingSource<Int, Character>() {

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
		val page = params.key ?: DEFAULT_PAGE_INDEX
		return try {
			val response = api.load(page)


			val responseData = mutableListOf<Character>()
			val data = response.body()?.results ?: emptyList()
			responseData.addAll(data)


			return LoadResult.Page(
				responseData, prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
				nextKey = if (responseData.isEmpty()) null else page + 1
			)

		} catch (exception: IOException) {
			return LoadResult.Error(exception)
		} catch (exception: HttpException) {
			return LoadResult.Error(exception)
		}
	}


}