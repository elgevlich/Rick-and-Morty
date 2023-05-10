package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.domain.model.character.CharacterModel
import com.example.rickandmorty.domain.model.character.Character
import com.example.rickandmorty.domain.model.character.CharacterResult

interface CharacterRepository {

	suspend fun getCharacter(
		page: Int,
		name: String,
		status: String,
		gender: String
	): CharacterModel

	suspend fun insertCharacter(list: List<CharacterResult>)

	fun getListCharactersDb(): List<CharacterResult>
}