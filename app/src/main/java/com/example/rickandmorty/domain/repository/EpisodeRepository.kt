package com.example.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.example.rickandmorty.domain.model.episode.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {

	suspend fun getEpisodes(name: String, episode: String): Flow<PagingData<Episode>>

}