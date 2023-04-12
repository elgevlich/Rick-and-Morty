package com.example.rickandmorty.presentation.fragments.characters

import androidx.lifecycle.*
import androidx.paging.PagingData

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig

import androidx.paging.cachedIn
import com.example.rickandmorty.data.model.Character
import kotlinx.coroutines.flow.Flow

import com.example.rickandmorty.data.network.CharacterApi
import com.example.rickandmorty.data.network.CharactersPagingSource


class CharactersViewModel(private val api: CharacterApi) : ViewModel() {

    val characters: Flow<PagingData<Character>>  = Pager(PagingConfig(pageSize = 20, prefetchDistance = 2)) {
        CharactersPagingSource(api)
    }.flow.cachedIn(viewModelScope)

}