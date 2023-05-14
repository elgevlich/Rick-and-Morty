package com.example.rickandmorty.presentation.fragments.locations.list


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.domain.model.location.LocationResult
import com.example.rickandmorty.domain.usecases.location.GetLocationUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


class LocationsListViewModel @Inject constructor(
	private val getLocationUseCase: GetLocationUseCase
) : ViewModel() {

	var locationFlow: Flow<PagingData<LocationResult>> = emptyFlow()

	fun load(name: String, type: String, dimension: String) {
		locationFlow = Pager(PagingConfig(pageSize = 1)) {
			getLocationUseCase.getLocation(name, type, dimension)
		}.flow.cachedIn(viewModelScope)
			.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
	}
}