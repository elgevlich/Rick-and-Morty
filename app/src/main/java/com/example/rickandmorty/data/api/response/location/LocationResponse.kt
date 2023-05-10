package com.example.rickandmorty.data.api.response.location

import com.example.rickandmorty.data.api.response.PageInfoResponse

data class LocationResponse(
	val info: PageInfoResponse?,
	val results: List<LocationResultResponse>
)