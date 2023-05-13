package com.example.rickandmorty.data.database.entity.character

data class CharacterDb(
	var info: CharacterInfoDb,
	var results: List<CharacterDbModel>
)