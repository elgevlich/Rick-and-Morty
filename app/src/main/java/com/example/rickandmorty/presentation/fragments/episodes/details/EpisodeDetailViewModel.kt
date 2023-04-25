package com.example.rickandmorty.presentation.fragments.episodes.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.api.RetrofitInstance
import com.example.rickandmorty.domain.model.episode.Episode
import com.example.rickandmorty.domain.model.character.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EpisodeDetailViewModel : ViewModel() {

	var episodeLiveData = MutableLiveData<Episode>()
	var charactersListLiveData = MutableLiveData<List<Character>>()
	var isLoading = MutableLiveData<Boolean>()
	var isNoCharacters = MutableLiveData<Boolean>()
	var isNotEnoughCharactersFound = MutableLiveData<Boolean>()
	private val characterApi = RetrofitInstance.characterApi
	private val episodeApi = RetrofitInstance.episodeApi

	fun getEpisodeId(id: Int) {
		isLoading.value = true
		viewModelScope.launch(Dispatchers.IO) {
			val result = episodeApi.getEpisodeId(id)
			launch(Dispatchers.Main) {
				result?.let {
					episodeLiveData.postValue(it)}
				isLoading.value = false
			}
		}
	}

	fun getCharactersId(charactersUrlList: List<String>) {
		if (charactersUrlList.isNullOrEmpty()) {
			updateIsNoCharacters()
		}else {
			isLoading.value = true
			viewModelScope.launch(Dispatchers.IO) {
				val result = ArrayList<Character>()
				for (characterUrl in charactersUrlList) {
					val id = characterUrl.split("/").last().toInt()
					val character = characterApi.getCharacterId(id)
					character?.let {
						result.add(it) }
				}
				launch(Dispatchers.Main) {
					updateCharactersListLiveData(result, charactersUrlList)
				}
			}
		}
	}

	private fun updateCharactersListLiveData(charactersList: List<Character>?,
		charactersUrlList: List<String>) {
		this.charactersListLiveData.value = charactersList
		isLoading.value = false
		if (charactersList != null) {
			if (charactersList.size < charactersUrlList.size) {
				updateIsNotEnoughCharactersFound()
			}
		}
	}

	private fun updateIsNoCharacters() {
		isNoCharacters.value = true
	}

	private fun updateIsNotEnoughCharactersFound() {
		isNotEnoughCharactersFound.value = true
	}
}