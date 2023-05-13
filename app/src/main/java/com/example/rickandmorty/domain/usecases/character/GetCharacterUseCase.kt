package com.example.rickandmorty.domain.usecases.character

import android.app.Application
import com.example.rickandmorty.data.pagingSource.CharacterPagingSource
import com.example.rickandmorty.data.repository.CharacterRepositoryImpl
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
	private val repository: CharacterRepositoryImpl,
	private val application: Application
) {

	fun getCharacter(name: String, status: String, gender: String): CharacterPagingSource {
		return CharacterPagingSource(repository, application, name, status, gender)
	}

}