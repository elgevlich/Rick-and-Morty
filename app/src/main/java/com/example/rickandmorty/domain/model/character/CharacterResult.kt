package com.example.rickandmorty.domain.model.character

data class CharacterResult (
	var created: String,
	var episode: List<String>,
	var gender: String,
	var id: Int,
	var image: String,
	var location: CharacterLocation,
	var name: String,
	var species: String,
	var status: String,
	var type: String,
	var url: String,
	var origin: CharacterOrigin
)