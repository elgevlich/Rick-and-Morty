package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.api.EpisodeApi
import com.example.rickandmorty.data.database.dao.EpisodeDao
import com.example.rickandmorty.data.mappers.EpisodeMapper
import com.example.rickandmorty.domain.model.episode.Episode
import com.example.rickandmorty.domain.model.episode.EpisodeResult
import com.example.rickandmorty.domain.repository.EpisodeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
	private val apiService: EpisodeApi,
	private val episodeDao: EpisodeDao,
	private val mapper: EpisodeMapper
) : EpisodeRepository {

	override suspend fun getEpisode(page: Int, name: String, episode: String): Episode {
		val episodeApi = apiService.getEpisodes(page, name, episode)
		val listEpisodes = mapper.mapEpisodeResponseForEpisode(episodeApi)
		episodeDao.insertEpisode(mapper.mapListResultResponseForListDb(listEpisodes.results))
		return listEpisodes
	}

	override suspend fun insertEpisode(list: List<EpisodeResult>) {
		episodeDao.insertEpisode(mapper.mapListResultResponseForListDb(list))
	}

	override fun getListEpisodesDb(): List<EpisodeResult> {
		var listEpisodes = emptyList<EpisodeResult>()
		CoroutineScope(Dispatchers.IO).launch {
			listEpisodes = (episodeDao.getAllEpisodes()).map {
				mapper.mapEpisodeResultDbForEpisodeResult(it)
			}
		}
		return listEpisodes
	}
}