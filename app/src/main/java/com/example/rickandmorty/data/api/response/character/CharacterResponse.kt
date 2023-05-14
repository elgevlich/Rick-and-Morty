package com.example.rickandmorty.data.api.response.character

import com.example.rickandmorty.data.api.response.PageInfoResponse

data class CharacterResponse (
	val info: PageInfoResponse?,
	var results: List<CharacterResultResponse>
)