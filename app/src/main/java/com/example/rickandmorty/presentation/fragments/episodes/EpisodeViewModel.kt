package com.example.rickandmorty.presentation.fragments.episodes

import androidx.lifecycle.*

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.data.api.EpisodeApi
import com.example.rickandmorty.data.pagingSource.EpisodePagingSource
import com.example.rickandmorty.domain.model.episode.Episode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.stateIn


class EpisodeViewModel(private val api: EpisodeApi) : ViewModel() {

	val dataEpisode = MutableLiveData<Episode>()

	var episodeFlow: Flow<PagingData<Episode>> = emptyFlow()

	fun load(name: String, episode: String) {
		episodeFlow = Pager(PagingConfig(pageSize = 1)) {
			EpisodePagingSource(name, episode, api)
		}.flow.cachedIn(viewModelScope)
			.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
	}

}