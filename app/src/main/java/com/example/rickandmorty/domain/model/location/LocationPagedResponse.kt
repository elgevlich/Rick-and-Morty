package com.example.rickandmorty.domain.model.location

import com.example.rickandmorty.domain.model.PageInfo
import com.google.gson.annotations.SerializedName

data class LocationPagedResponse<T>(
	@SerializedName("info") val pageInfo: PageInfo,
	val results: List<Location> = listOf()
)