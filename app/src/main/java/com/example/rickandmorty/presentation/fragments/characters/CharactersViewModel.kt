package com.example.rickandmorty.presentation.fragments.characters

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.Repository
import com.example.rickandmorty.data.model.CharactersList
import kotlinx.coroutines.launch
import retrofit2.Response

class CharactersViewModel(application: Application) : AndroidViewModel(application) {
	var listCharactersInEpisode = MutableLiveData<List<Character>>()

	private val repository = Repository()
	var charactersList: MutableLiveData<Response<CharactersList>> = MutableLiveData()

	fun getCharacters(page: Int) {
		viewModelScope.launch {
			val characters = repository.getCharacters(page)

		}
	}
}