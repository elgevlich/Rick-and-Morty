package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.api.EpisodeApi
import com.example.rickandmorty.data.database.dao.EpisodeDao
import com.example.rickandmorty.data.mappers.EpisodeMapper
import com.example.rickandmorty.domain.model.character.CharacterResult
import com.example.rickandmorty.domain.model.episode.Episode
import com.example.rickandmorty.domain.model.episode.EpisodeResult
import com.example.rickandmorty.domain.repository.EpisodeRepository
import io.reactivex.Observable
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

    override suspend fun getListEpisodesDb(
        offset: Int,
        limit: Int,
        name: String,
        episode: String
    ): List<EpisodeResult> {
        return episodeDao.getAllEpisodesPage(offset, limit, name, episode)
            .map(mapper::mapEpisodeResultDbForEpisodeResult)
    }

    override fun getDetailCharacter(id: String): Observable<List<CharacterResult>> {
        return apiService.getDetailCharacter(id)
    }
}