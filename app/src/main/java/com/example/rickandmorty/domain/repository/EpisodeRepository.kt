package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.domain.model.episode.Episode
import com.example.rickandmorty.domain.model.episode.EpisodeResult

interface EpisodeRepository {

	suspend fun getEpisode(page: Int, name: String, episode: String): Episode

	suspend fun insertEpisode(list: List<EpisodeResult>)

	fun getListEpisodesDb(): List<EpisodeResult>

}