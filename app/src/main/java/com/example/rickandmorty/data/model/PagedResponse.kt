package com.example.rickandmorty.data.model

import com.google.gson.annotations.SerializedName


data class PagedResponse<T>(
 @SerializedName("info") val pageInfo: PageInfo,
 val results: List<Character> = listOf()
)

