package com.example.rickandmorty.presentation.fragments.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.data.api.EpisodeApi


class EpisodeViewModelFactory(private val api: EpisodeApi) :
	ViewModelProvider.Factory {

	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(EpisodeViewModel::class.java)) {
			return EpisodeViewModel(api) as T
		}
		throw IllegalArgumentException("Unknown ViewModel class")
	}
}