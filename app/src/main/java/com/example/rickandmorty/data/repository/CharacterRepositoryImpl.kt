package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.api.CharacterApi
import com.example.rickandmorty.data.database.dao.CharacterDao
import com.example.rickandmorty.data.mappers.CharacterMapper
import com.example.rickandmorty.domain.model.character.CharacterModel
import com.example.rickandmorty.domain.model.character.CharacterResult
import com.example.rickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
	private val apiService: CharacterApi,
	private val characterDao: CharacterDao,
	private val mapper: CharacterMapper
) : CharacterRepository {

	override suspend fun getCharacter(page: Int, name: String, status: String, gender: String): CharacterModel {
		val characterApi = apiService.getCharacters(page, name, status, gender)
		val listCharacters = mapper.mapCharacterResponseForCharacter(characterApi)
		characterDao.insertCharacter(mapper.mapListResultResponseForListDb(listCharacters.result))
		return listCharacters
	}

	override suspend fun insertCharacter(list: List<CharacterResult>) {
		characterDao.insertCharacter(mapper.mapListResultResponseForListDb(list))
	}

	override fun getListCharactersDb(): List<CharacterResult> {
		var listCharacters = emptyList<CharacterResult>()
		CoroutineScope(Dispatchers.IO).launch {
			listCharacters = (characterDao.getAllCharacters()).map {
				mapper.mapCharacterResultDbForCharacterResult(it)
			}
		}
		return listCharacters
	}
}