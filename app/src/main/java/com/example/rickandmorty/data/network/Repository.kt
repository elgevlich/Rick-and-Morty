package com.example.rickandmorty.data.network

import com.example.rickandmorty.data.model.CharacterList

class Repository {

    suspend fun getCharacters(page: Int): CharacterList {
        return RetrofitInstance.api.getCharacters(page)
    }

    suspend fun getCharactersByName(name: String): CharacterList{
        return RetrofitInstance.api.getCharactersByName(name)
    }

    suspend fun getCharactersByStatusAndGender(status : String, gender: String, page:Int): CharacterList{
        return RetrofitInstance.api.getCharactersByStatusAndGender(status, gender, page)
    }

    suspend fun getCharactersByStatus(status : String, page:Int): CharacterList{
        return RetrofitInstance.api.getCharactersByStatus(status, page)
    }

    suspend fun getCharactersByGender(gender : String, page:Int): CharacterList{
        return RetrofitInstance.api.getCharactersByGender(gender, page)
    }
}
