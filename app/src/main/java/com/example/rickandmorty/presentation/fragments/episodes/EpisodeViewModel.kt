package com.example.rickandmorty.presentation.fragments.episodes

import androidx.lifecycle.*

import androidx.lifecycle.viewModelScope

import com.example.rickandmorty.data.model.Episode

import com.example.rickandmorty.data.network.RepositoryEpisodes
import kotlinx.coroutines.launch


class EpisodeViewModel(private val repository: RepositoryEpisodes) : ViewModel() {

	var listEpisodes = MutableLiveData<List<Episode>>()
	var filterValue = MutableLiveData<Array<Int>>()
	var isFilter = MutableLiveData<Boolean>()

	init {
		filterValue.value = arrayOf(0, 0)
		isFilter.value = false
	}

	fun getEpisodes(page: Int) {
		viewModelScope.launch {
			val characters = repository.getEpisodes(page)
			listEpisodes.value = characters.results
			isFilter.value = false
		}
	}

	fun getEpisodesByName(name: String) {
		viewModelScope.launch {
			val characters = repository.getEpisodesByName(name)
			listEpisodes.value = characters.results
			isFilter.value = true
		}
	}


}