package com.example.rickandmorty.presentation.fragments.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.data.api.CharacterApi


class CharacterViewModelFactory(private val api: CharacterApi) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            return CharacterViewModel(api) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}