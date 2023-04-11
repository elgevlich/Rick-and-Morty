package com.example.rickandmorty.presentation.fragments.characters

import androidx.lifecycle.*
import androidx.paging.PagingData
import com.example.rickandmorty.data.Repository
import com.example.rickandmorty.data.model.Character
import kotlinx.coroutines.launch

class CharactersViewModel( private val repository: Repository = Repository.getInstance()) : ViewModel() {


	var charactersList: MutableLiveData<List<Character>> = MutableLiveData()


	fun fetchDoggoImages(): Flow<PagingData<String>> {
		return repository.letDoggoImagesFlow()
			.map { it.map { it.url } }
			.cachedIn(viewModelScope)
	}

	fun getCharacters(): LiveData<PagingData<String>> {
		return repository.letDoggoImagesLiveData()
			.map { it.map { it.url } }
			.cachedIn(viewModelScope)
	}

	fun getCharactes() {
		viewModelScope.launch {
			val characters = repository.getCharacters()
			charactersList.value = characters.results

		}
	}
}