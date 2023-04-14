package com.example.rickandmorty.data.network


import com.example.rickandmorty.data.model.Character


interface CharacterRepository {
    suspend fun getCharacter(page: Int, name: String, status: String, gender: String): Character
    suspend fun getFilteredCharacter(name: String):Character
    suspend fun getDetailCharacter(id: Int): Character
}