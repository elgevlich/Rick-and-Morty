package com.example.rickandmorty.presentation.fragments.locations


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.model.Location
import com.example.rickandmorty.data.network.RepositoryLocations
import kotlinx.coroutines.launch

class LocationViewModel(private val repository: RepositoryLocations) : ViewModel() {

	var listLocations = MutableLiveData<List<Location>>()
	var filterValue = MutableLiveData<Array<Int>>()
	var isFilter = MutableLiveData<Boolean>()

	init {
		filterValue.value = arrayOf(0, 0)
		isFilter.value = false
	}

	fun getLocations(page: Int) {
		viewModelScope.launch {
			val characters = repository.getLocations(page)
			listLocations.value = characters.results
			isFilter.value = false
		}
	}

	fun getLocationsByName(name: String) {
		viewModelScope.launch {
			val characters = repository.getLocationsByName(name)
			listLocations.value = characters.results
			isFilter.value = true
		}
	}


}