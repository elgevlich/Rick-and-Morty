package com.example.rickandmorty.presentation.fragments.locations.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.data.api.LocationApi

class LocationViewModelFactory(private val api: LocationApi) :
	ViewModelProvider.Factory {

	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(LocationViewModel::class.java)) {
			return LocationViewModel(api) as T
		}
		throw IllegalArgumentException("Unknown ViewModel class")
	}
}