package com.example.rickandmorty.data.network

import com.example.rickandmorty.data.model.CharacterList

class RepositoryCharacters {

    suspend fun getCharacters(page: Int): CharacterList {
        return RetrofitInstance.characterApi.getCharacters(page)
    }

    suspend fun getCharactersByName(name: String): CharacterList{
        return RetrofitInstance.characterApi.getCharactersByName(name)
    }

    suspend fun getCharactersByStatusAndGender(status : String, gender: String, page:Int): CharacterList{
        return RetrofitInstance.characterApi.getCharactersByStatusAndGender(status, gender, page)
    }

    suspend fun getCharactersByStatus(status : String, page:Int): CharacterList{
        return RetrofitInstance.characterApi.getCharactersByStatus(status, page)
    }

    suspend fun getCharactersByGender(gender : String, page:Int): CharacterList{
        return RetrofitInstance.characterApi.getCharactersByGender(gender, page)
    }
}
