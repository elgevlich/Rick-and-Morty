package com.example.rickandmorty.presentation.fragments.locations


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.data.api.LocationApi
import com.example.rickandmorty.data.pagingSource.LocationPagingSource
import com.example.rickandmorty.domain.model.location.Location
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.stateIn


class LocationViewModel(private val api: LocationApi) : ViewModel() {

	val dataLocation = MutableLiveData<Location>()

	var locationFlow: Flow<PagingData<Location>> = emptyFlow()

	fun load(name: String, type: String, dimension: String) {
		locationFlow = Pager(PagingConfig(pageSize = 1)) {
			LocationPagingSource(name, type, dimension, api)
		}.flow.cachedIn(viewModelScope)
			.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
	}

}