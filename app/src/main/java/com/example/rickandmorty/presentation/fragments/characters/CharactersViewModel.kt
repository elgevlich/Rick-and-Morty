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


class CharactersViewModel(val api: CharacterApi) : ViewModel() {

    val charactersFlow: Flow<PagingData<Character>> =
        Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = { CharactersPagingSource(api) }
        ).flow.cachedIn(viewModelScope)
}