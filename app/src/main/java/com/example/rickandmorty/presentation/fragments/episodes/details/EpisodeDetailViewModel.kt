package com.example.rickandmorty.presentation.fragments.episodes.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.api.RetrofitInstance.characterApi
import com.example.rickandmorty.domain.model.character.CharacterResult
import com.example.rickandmorty.domain.model.episode.EpisodeResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class EpisodeDetailViewModel : ViewModel() {

	val selectedItemEpisode = MutableLiveData<EpisodeResult>()
	val responseCharacters = MutableLiveData<List<CharacterResult?>?>()
	private val listOfCharacters = mutableListOf<List<String>>()
	private var characterId: String? = null
	private val apiService = characterApi
	private val compositeDisposable = CompositeDisposable()

	fun onClickItemEpisode(episode: EpisodeResult) {
		selectedItemEpisode.value = episode
		listOfCharacters.add(episode.characters)
	}

	fun fetchData() {
		compositeDisposable.add(apiService.getListOfCharactersForDetails(characterId!!)
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(
				{ character: List<CharacterResult?>? ->
					this.responseCharacters.value = character
				}) { })
	}

	fun getCharacters() {
		var str1: String
		var result = ""
		if (listOfCharacters.isNotEmpty()) {
			for (episode in listOfCharacters[0]) {
				str1 = episode.substring(42)
				result = "$result+$str1,"
			}
		}
		characterId = result
	}

	fun clearListOfCharacters() {
		listOfCharacters.clear()
	}
}