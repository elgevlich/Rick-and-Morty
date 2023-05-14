package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.domain.model.character.CharacterResult
import com.example.rickandmorty.domain.model.episode.Episode
import com.example.rickandmorty.domain.model.episode.EpisodeResult
import io.reactivex.Observable

interface EpisodeRepository {

    suspend fun getEpisode(page: Int, name: String, episode: String): Episode

    suspend fun insertEpisode(list: List<EpisodeResult>)

    suspend fun getListEpisodesDb(
        offset: Int,
        limit: Int,
        name: String,
        episode: String
    ): List<EpisodeResult>

    fun getDetailCharacter(id: String): Observable<List<CharacterResult>>
}