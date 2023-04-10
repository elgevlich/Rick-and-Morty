package com.example.rickandmorty.data


import com.example.rickandmorty.data.model.CharacterList
import com.example.rickandmorty.data.network.RetrofitInstance

class Repository {

	suspend fun getCharacters(page: Int): CharacterList {
		return RetrofitInstance.api.getCharacters(page)
	}
}