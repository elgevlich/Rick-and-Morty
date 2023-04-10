package com.example.rickandmorty.data


import com.example.rickandmorty.data.model.CharactersList
import com.example.rickandmorty.data.network.RetrofitInstance
import retrofit2.Response

class Repository {

	suspend fun getCharacters(page: Int): CharactersList {
		return RetrofitInstance.api.getCharacters(page)
	}
}