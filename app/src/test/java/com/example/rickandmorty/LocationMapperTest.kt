package com.example.rickandmorty

import com.example.rickandmorty.data.api.response.location.LocationResultResponse
import com.example.rickandmorty.data.database.entity.location.LocationDbModel
import com.example.rickandmorty.data.mappers.LocationMapper
import com.example.rickandmorty.domain.model.location.LocationResult
import junit.framework.Assert.assertEquals
import org.junit.Test

internal class LocationMapperTest{

    private val locationMapper = LocationMapper()

    @Test
    fun resultResponseToLocationResult(){
        val locationResultResponse = LocationResultResponse(
            created = LOCATION_CREATED,
            dimension = LOCATION_DIMENSION,
            id = LOCATION_ID,
            name = LOCATION_NAME,
            residents = LOCATION_RESIDENTS,
            type = LOCATION_TYPE,
            url = LOCATION_URL
        )

        val expectedLocationResult = LocationResult(
            created = LOCATION_CREATED,
            dimension = LOCATION_DIMENSION,
            id = LOCATION_ID,
            name = LOCATION_NAME,
            residents = LOCATION_RESIDENTS,
            type = LOCATION_TYPE,
            url = LOCATION_URL
        )

        val actualLocationResult: LocationResult = locationMapper.mapResultsResponseForResults(locationResultResponse)

        assertEquals(expectedLocationResult,actualLocationResult)
    }

    @Test
    fun locationResultToLocationModelDb(){
        val locationResult = LocationResult(
            created = LOCATION_CREATED,
            dimension = LOCATION_DIMENSION,
            id = LOCATION_ID,
            name = LOCATION_NAME,
            residents = LOCATION_RESIDENTS,
            type = LOCATION_TYPE,
            url = LOCATION_URL
        )

        val expectedLocationDbModel = LocationDbModel(
            created = LOCATION_CREATED,
            dimension = LOCATION_DIMENSION,
            id = LOCATION_ID,
            name = LOCATION_NAME,
            type = LOCATION_TYPE,
            url = LOCATION_URL
        )

        val actualLocationDbModel: LocationDbModel = locationMapper.mapLocationResultResponseForLocationResultDb(locationResult)

        assertEquals(expectedLocationDbModel, actualLocationDbModel)
    }

    @Test
    fun locationModelDbToLocationResult(){
        val locationDbModel = LocationDbModel(
            created = LOCATION_CREATED,
            dimension = LOCATION_DIMENSION,
            id = LOCATION_ID,
            name = LOCATION_NAME,
            type = LOCATION_TYPE,
            url = LOCATION_URL
        )

        val expectedLocationResult = LocationResult(
            created = LOCATION_CREATED,
            dimension = LOCATION_DIMENSION,
            id = LOCATION_ID,
            name = LOCATION_NAME,
            residents = emptyList(),
            type = LOCATION_TYPE,
            url = LOCATION_URL
        )

        val actualLocationResult: LocationResult = locationMapper.mapLocationResultDbForLocationResult(locationDbModel)

        assertEquals(expectedLocationResult, actualLocationResult)
    }

    companion object {
        private const val LOCATION_CREATED = "123"
        private const val LOCATION_DIMENSION = "some dimension"
        private const val LOCATION_ID = 123
        private const val LOCATION_NAME = "some name"
        private val LOCATION_RESIDENTS = listOf(
            "https://test.com/api/character/1",
            "https://test.com/api/character/2",
            "https://test.com/api/character/3",
        )
        private const val LOCATION_TYPE = "some type"
        private const val LOCATION_URL = "some url"
    }
}