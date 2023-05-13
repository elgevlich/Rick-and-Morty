package com.example.rickandmorty.presentation.fragments.characters.list

import androidx.lifecycle.*

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.domain.model.character.CharacterResult
import com.example.rickandmorty.domain.usecases.character.GetCharacterUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


class CharactersListViewModel @Inject constructor(
	private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {

	var characterFlow: Flow<PagingData<CharacterResult>> = emptyFlow()

	fun load(name: String, status: String, gender: String) {
		characterFlow = Pager(PagingConfig(pageSize = 1)) {
			getCharacterUseCase.getCharacter(name, status, gender)
		}.flow.cachedIn(viewModelScope)
			.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
	}
}
