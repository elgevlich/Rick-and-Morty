package com.example.rickandmorty.domain.model.character

import com.example.rickandmorty.domain.model.PageInfo
import com.google.gson.annotations.SerializedName


data class CharacterPagedResponse<T>(
    @SerializedName("info") val pageInfo: PageInfo,
    val results: List<Character> = listOf()
)
