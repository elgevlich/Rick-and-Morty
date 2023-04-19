package com.example.rickandmorty.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.data.api.EpisodeApi
import com.example.rickandmorty.data.pagingSource.EpisodePagingSource
import com.example.rickandmorty.domain.model.episode.Episode
import com.example.rickandmorty.domain.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow

class EpisodeRepositoryImpl(
	private val service: EpisodeApi,
) : EpisodeRepository {

	override suspend fun getEpisodes(
		name: String,
		episode: String,
	): Flow<PagingData<Episode>> = Pager(
		config = PagingConfig(pageSize = 20, prefetchDistance = 2),
		pagingSourceFactory = { EpisodePagingSource(name, episode, service) }
	).flow

}