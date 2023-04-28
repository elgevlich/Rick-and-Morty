package com.example.rickandmorty.domain.model.location

import com.example.rickandmorty.domain.model.PageInfo

data class LocationResponse(
	val info: PageInfo,
	val results: List<Location>
)