package com.example.rickandmorty.domain.model.episode

import com.example.rickandmorty.domain.model.PageInfo
import com.google.gson.annotations.SerializedName

data class EpisodePagedResponse<T>(
	@SerializedName("info") val pageInfo: PageInfo,
	val results: List<Episode> = listOf()
)