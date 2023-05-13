package com.example.rickandmorty.data.mappers

import com.example.rickandmorty.data.api.response.PageInfoResponse
import com.example.rickandmorty.data.api.response.character.*
import com.example.rickandmorty.data.database.entity.character.CharacterDbModel
import com.example.rickandmorty.data.database.entity.character.CharacterLocationDb
import com.example.rickandmorty.domain.model.PageInfo
import javax.inject.Inject
import com.example.rickandmorty.domain.model.character.*

class CharacterMapper @Inject constructor() {

	private fun mapCharacterResponseForInfo(pageResponseInfo: PageInfoResponse?) = PageInfo(
		pages = pageResponseInfo?.pages ?: ZERO_NUMBER,
		next = pageResponseInfo?.next ?: EMPTY_STRING,
		count = pageResponseInfo?.count ?: ZERO_NUMBER,
		prev = pageResponseInfo?.prev ?: EMPTY_STRING
	)

	private fun mapLocationResponseForLocation(locationResponse: CharacterLocationResponse?) = CharacterLocation(
		name = locationResponse?.name ?: EMPTY_STRING,
		url = locationResponse?.url ?: EMPTY_STRING
	)

	private fun mapResultResponseForResult(resultResponse: CharacterResultResponse?) = CharacterResult(
		id = resultResponse?.id ?: ZERO_NUMBER,
		created = resultResponse?.created ?: EMPTY_STRING,
		episode = resultResponse?.episode ?: emptyList(),
		gender = resultResponse?.gender ?: EMPTY_STRING,
		image = resultResponse?.image ?: EMPTY_STRING,
		location = mapLocationResponseForLocation(resultResponse?.location),
		name = resultResponse?.name ?: EMPTY_STRING,
		species = resultResponse?.species ?: EMPTY_STRING,
		status = resultResponse?.status ?: EMPTY_STRING,
		type = resultResponse?.type ?: EMPTY_STRING,
		url = resultResponse?.url ?: EMPTY_STRING,
		origin = mapOriginResponseForOrigin(resultResponse?.origin!!)
	)

	private fun mapListCharacterResponseForResult(list: List<CharacterResultResponse>) = list.map {
		mapResultResponseForResult(it)
	}

	fun mapCharacterResponseForCharacter(characterResponse: CharacterResponse) = CharacterModel(
		info = mapCharacterResponseForInfo(characterResponse.info),
		result = mapListCharacterResponseForResult(characterResponse.results)
	)

	fun mapLocationResponseForLocationDb(location: CharacterLocation) = CharacterLocationDb(
		name = location.name ?: EMPTY_STRING,
		url = location.url ?: EMPTY_STRING
	)


	fun mapLocationDbForLocation(location: CharacterLocationDb) = CharacterLocation(
		name = location.name ?: EMPTY_STRING,
		url = location.url ?: EMPTY_STRING
	)

	private fun mapCharacterResultResponseForCharacterResultDb(characterResult: CharacterResult): CharacterDbModel {
		return CharacterDbModel(
			id = characterResult.id,
			name = characterResult.name,
			gender = characterResult.gender,
			status = characterResult.status,
			species = characterResult.species,
			image = characterResult.image,
			url = characterResult.url,
			type = characterResult.type,
			created = characterResult.created,
			location = characterResult.location.name.toString(),
			origin = characterResult.origin.name

		)
	}


	fun mapCharacterResultDbForCharacterResult(characterResult: CharacterDbModel): CharacterResult {
		return CharacterResult(
			created = characterResult.created,
			id = characterResult.id,
			status = characterResult.status,
			name = characterResult.name,
			species = characterResult.species,
			image = characterResult.image,
			location = CharacterLocation(characterResult.location, ""),
			url = characterResult.url,
			type = characterResult.type,
			episode = emptyList(),
			gender = characterResult.gender,
			origin = CharacterOrigin(characterResult.origin, "")
		)
	}

	fun mapListResultResponseForListDb(list: List<CharacterResult>) = list.map {
		mapCharacterResultResponseForCharacterResultDb(it)
	}

	fun mapListResultDbForCharacterResult(list: List<CharacterDbModel>) = list.map {
		mapCharacterResultDbForCharacterResult(it)
	}

	fun mapDetailResponseForDetail(characterDetailResponse: CharacterDetailResponse): CharacterDetail {
		return CharacterDetail(
			created = characterDetailResponse.created,
			gender = characterDetailResponse.gender,
			name = characterDetailResponse.name,
			species = characterDetailResponse.species,
			status = characterDetailResponse.status,
			episode = characterDetailResponse.episode,
			type = characterDetailResponse.type,
			image = characterDetailResponse.image,
			location = mapLocationResponseForLocation(characterDetailResponse.location),
			id = characterDetailResponse.id,
			url = characterDetailResponse.url,
			origin = mapOriginResponseForOrigin(characterDetailResponse.origin),
		)
	}

	private fun mapOriginResponseForOrigin(originResponse: CharacterOriginResponse): CharacterOrigin {
		return CharacterOrigin(
			name = originResponse.name,
			url = originResponse.url
		)
	}

	companion object {

		private const val EMPTY_STRING = ""
		private const val ZERO_NUMBER = 0
	}

}