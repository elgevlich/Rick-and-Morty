package com.example.rickandmorty.presentation.fragments.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.domain.repository.RepositoryLocations

class LocationViewModelFactory(private val repository: RepositoryLocations) : ViewModelProvider.Factory {

	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(LocationViewModel::class.java)) {
			return LocationViewModel(repository) as T
		}
		throw IllegalArgumentException("Unknown ViewModel class")
	}
}