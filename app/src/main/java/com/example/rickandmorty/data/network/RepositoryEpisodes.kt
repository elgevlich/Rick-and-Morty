package com.example.rickandmorty.data.network

import com.example.rickandmorty.data.model.EpisodeList

class RepositoryEpisodes {

	suspend fun getEpisodes(page: Int): EpisodeList {
		return RetrofitInstance.episodeApi.getEpisodes(page)
	}

	suspend fun getEpisodesByName(name: String): EpisodeList {
		return RetrofitInstance.episodeApi.getEpisodesByName(name)
	}
}