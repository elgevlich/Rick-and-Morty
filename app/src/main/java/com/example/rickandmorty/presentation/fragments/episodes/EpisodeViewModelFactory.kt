package com.example.rickandmorty.presentation.fragments.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.data.network.RepositoryCharacters
import com.example.rickandmorty.data.network.RepositoryEpisodes

class EpisodeViewModelFactory(private val repository: RepositoryEpisodes) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EpisodeViewModel::class.java)) {
            return EpisodeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}