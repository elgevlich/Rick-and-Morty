package com.example.rickandmorty.presentation

import com.example.rickandmorty.domain.model.episode.Episode
import com.example.rickandmorty.domain.model.character.Character
import com.example.rickandmorty.domain.model.location.Location

interface Repository {

	suspend fun getCharacterId(id : Int) : Character?

	suspend fun getEpisodeId(id : Int): Episode?

	suspend fun getLocationId(id: Int): Location?

}