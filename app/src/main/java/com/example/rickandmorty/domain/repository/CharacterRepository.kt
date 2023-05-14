package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.domain.model.character.CharacterModel
import com.example.rickandmorty.domain.model.character.CharacterResult
import com.example.rickandmorty.domain.model.episode.EpisodeResult
import io.reactivex.Observable

interface CharacterRepository {

    suspend fun getCharacter(
        page: Int,
        name: String,
        status: String,
        gender: String
    ): CharacterModel

    suspend fun insertCharacter(list: List<CharacterResult>)


    suspend fun getListCharacters(
        offset: Int,
        limit: Int,
        name: String,
        gender: String,
        status: String
    ): List<CharacterResult>

    fun getDetailEpisode(id: String): Observable<List<EpisodeResult>>
}