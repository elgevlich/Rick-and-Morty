package com.example.rickandmorty.presentation.fragments.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.data.network.CharacterApi

class CharacterViewModelFactory(private val api: CharacterApi) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharactersViewModel::class.java)) {
            return CharactersViewModel(api) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}