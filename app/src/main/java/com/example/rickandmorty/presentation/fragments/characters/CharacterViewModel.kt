package com.example.rickandmorty.presentation.fragments.characters

import androidx.lifecycle.*

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn

import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.data.network.ApiService
import com.example.rickandmorty.data.network.CharacterPagingSource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.stateIn


class CharacterViewModel(private val api: ApiService) : ViewModel() {

	val dataCharacter = MutableLiveData<Character>()

	var characterFlow: Flow<PagingData<Character>> = emptyFlow()

	fun load(name: String, status: String, gender: String){
		characterFlow = Pager(PagingConfig(pageSize = 1)) {
			CharacterPagingSource(status, gender)
		}.flow.cachedIn(viewModelScope).stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
	}

	val characters: Flow<PagingData<Character>> =
		Pager(PagingConfig(pageSize = 20, prefetchDistance = 2)) {
			CharacterPagingSource(api)
		}.flow.cachedIn(viewModelScope)

}