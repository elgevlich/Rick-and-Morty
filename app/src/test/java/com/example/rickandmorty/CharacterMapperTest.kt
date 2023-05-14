package com.example.rickandmorty

import com.example.rickandmorty.data.database.entity.character.CharacterDbModel
import com.example.rickandmorty.data.mappers.CharacterMapper


import com.example.rickandmorty.domain.model.character.CharacterLocation
import com.example.rickandmorty.domain.model.character.CharacterOrigin
import com.example.rickandmorty.domain.model.character.CharacterResult
import junit.framework.Assert.assertEquals
import org.junit.Test

internal class CharacterMapperTest {

    private val characterMapper = CharacterMapper()

    @Test
    fun modelToDbCharacter() {

        val characterResult = CharacterResult(
            id = CHARACTER_ID,
            name = CHARACTER_NAME,
            status = CHARACTER_STATUS,
            species = CHARACTER_SPECIES,
            type = CHARACTER_TYPE,
            gender = CHARACTER_GENDER,
            origin = CharacterOrigin(CHARACTER_ORIGIN_NAME, CHARACTER_ORIGIN_URL),
            location = CharacterLocation(CHARACTER_LOCATION_NAME, CHARACTER_LOCATION_URL),
            image = CHARACTER_IMAGE,
            episode = CHARACTER_EPISODES,
            url = CHARACTER_URL,
            created = CHARACTER_CREATED,
        )

        val expectedDbCharacter = CharacterDbModel(
            id = CHARACTER_ID,
            name = CHARACTER_NAME,
            status = CHARACTER_STATUS,
            species = CHARACTER_SPECIES,
            type = CHARACTER_TYPE,
            gender = CHARACTER_GENDER,
            origin = CHARACTER_ORIGIN_NAME,
            location = CHARACTER_LOCATION_NAME,
            image = CHARACTER_IMAGE,
            url = CHARACTER_URL,
            created = CHARACTER_CREATED,
        )


        val actualDbCharacter: CharacterDbModel =
            characterMapper.mapCharacterResultResponseForCharacterResultDb(characterResult)

        assertEquals(expectedDbCharacter, actualDbCharacter)

    }


    companion object {
        private const val CHARACTER_ID = 123
        private const val CHARACTER_NAME = "some name"
        private const val CHARACTER_STATUS = "dead"
        private const val CHARACTER_SPECIES = "some species"
        private const val CHARACTER_TYPE = "some type"
        private const val CHARACTER_GENDER = "some gender"
        private const val CHARACTER_ORIGIN_NAME = "some origin name"
        private const val CHARACTER_ORIGIN_URL = "some origin url"
        private const val CHARACTER_LOCATION_NAME = "some location name"
        private const val CHARACTER_LOCATION_URL = "some location url"
        private const val CHARACTER_IMAGE = "some image"
        private val CHARACTER_EPISODES = listOf(
            "https://test.com/api/episode/1",
            "https://test.com/api/episode/2",
            "https://test.com/api/episode/3",
        )
        private const val CHARACTER_URL = "some url"
        private const val CHARACTER_CREATED = "some created"
    }
}