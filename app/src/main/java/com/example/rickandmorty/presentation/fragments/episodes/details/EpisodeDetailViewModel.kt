package com.example.rickandmorty.presentation.fragments.episodes.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.domain.model.episode.Episode

class EpisodeDetailViewModel : ViewModel() {


	private var selectedItemEpisode = MutableLiveData<Episode>()
	var charactersList: MutableList<String> = java.util.ArrayList()
	var charactersIds: String? = null

	fun clearListOfCharacters() {
		charactersList.clear()
	}

	fun onClickItemEpisode(episode: Episode) {
		selectedItemEpisode.value = episode
		charactersList
			.addAll(
				episode
					.characters
			)
	}

	fun getSelectedItemEpisode(): MutableLiveData<Episode>? {
		return selectedItemEpisode
	}

	fun getCharacters() {
		var str1: String
		var result = ""
		if (charactersList.isNotEmpty()) {
			for (character in charactersList) {
				str1 = character.substring(42)
				result = "$result$str1,"
			}
		}
		charactersIds = result
	}


}