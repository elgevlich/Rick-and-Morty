package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.data.api.RetrofitInstance
import com.example.rickandmorty.domain.model.episode.EpisodeList

class RepositoryEpisodes {

	suspend fun getEpisodes(page: Int): EpisodeList {
		return RetrofitInstance.episodeApi.getEpisodes(page)
	}

	suspend fun getEpisodesByName(name: String): EpisodeList {
		return RetrofitInstance.episodeApi.getEpisodesByName(name)
	}
}