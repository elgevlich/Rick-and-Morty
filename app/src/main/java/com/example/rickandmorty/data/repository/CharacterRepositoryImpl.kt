package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.api.CharacterApi
import com.example.rickandmorty.data.database.dao.CharacterDao
import com.example.rickandmorty.data.mappers.CharacterMapper
import com.example.rickandmorty.domain.model.character.CharacterModel
import com.example.rickandmorty.domain.model.character.CharacterResult
import com.example.rickandmorty.domain.model.episode.EpisodeResult
import com.example.rickandmorty.domain.repository.CharacterRepository
import io.reactivex.Observable
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val apiService: CharacterApi,
    private val characterDao: CharacterDao,
    private val mapper: CharacterMapper
) : CharacterRepository {


    override suspend fun getCharacter(
        page: Int,
        name: String,
        status: String,
        gender: String
    ): CharacterModel {
        val characterApi = apiService.getCharacters(page, name, gender, status)
        val listCharacters = mapper.mapCharacterResponseForCharacter(characterApi)
        characterDao.insertCharacter(mapper.mapListResultResponseForListDb(listCharacters.result))
        return listCharacters
    }

    override suspend fun insertCharacter(list: List<CharacterResult>) {
        characterDao.insertCharacter(mapper.mapListResultResponseForListDb(list))
    }

    override suspend fun getListCharacters(
        offset: Int,
        limit: Int,
        name: String,
        gender: String,
        status: String
    ): List<CharacterResult> {
        return characterDao.getCharactersPage(offset, limit, name, gender, status)
            .map(mapper::mapCharacterResultDbForCharacterResult)
    }

    override fun getDetailEpisode(id: String): Observable<List<EpisodeResult>> {
        return apiService.getDetailEpisode(id)
    }
}