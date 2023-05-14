package com.example.rickandmorty.presentation.fragments.episodes.list

import androidx.lifecycle.*

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.domain.model.episode.EpisodeResult
import com.example.rickandmorty.domain.usecases.episode.GetEpisodeUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


class EpisodesListViewModel @Inject constructor(
	private val getEpisodeUseCase: GetEpisodeUseCase
) : ViewModel() {

	var episodeFlow: Flow<PagingData<EpisodeResult>> = emptyFlow()

	fun load(name: String, episode: String) {
		episodeFlow = Pager(PagingConfig(pageSize = 1)) {
			getEpisodeUseCase.getEpisodes(name, episode)
		}.flow.cachedIn(viewModelScope)
			.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
	}
}