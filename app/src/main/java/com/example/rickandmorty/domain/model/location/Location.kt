package com.example.rickandmorty.domain.model.location

import com.example.rickandmorty.domain.model.PageInfo

data class Location(
	val info: PageInfo,
	val results: List<LocationResult>
)