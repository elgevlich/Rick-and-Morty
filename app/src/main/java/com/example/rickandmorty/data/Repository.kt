package com.example.rickandmorty.data


import androidx.lifecycle.MutableLiveData
import androidx.paging.*

import com.example.rickandmorty.data.model.CharacterListResponse
import com.example.rickandmorty.data.network.ApiService

import java.util.concurrent.Flow

class Repository(

	private val apiService: ApiService = RemoteInjector.injectApiService(),

	) {

	companion object {

		const val DEFAULT_PAGE_INDEX = 1
		const val DEFAULT_PAGE_SIZE = 20

		fun getInstance() = Repository()
	}


	fun letCharactersFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<CharacterListResponse>> {
		return Pager(
			config = pagingConfig,
			pagingSourceFactory = { PagingSource(apiService) }
		).flow
	}


	fun letDoggoImagesLiveData(pagingConfig: PagingConfig = getDefaultPageConfig()): MutableLiveData<PagingData<CharacterListResponse>> {
		return Pager(
			config = pagingConfig,
			pagingSourceFactory = { PagingSource(apiService) }
		).liveData
	}


	private fun getDefaultPageConfig(): PagingConfig {
		return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
	}

}