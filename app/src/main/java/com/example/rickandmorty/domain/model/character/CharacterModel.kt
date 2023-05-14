package com.example.rickandmorty.domain.model.character

import com.example.rickandmorty.domain.model.PageInfo

data class CharacterModel(
	var info: PageInfo,
	var result: List<CharacterResult>
)