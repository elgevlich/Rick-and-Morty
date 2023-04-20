package com.example.rickandmorty.domain.model.episode

import com.google.gson.annotations.SerializedName

class EpisodeResponse {

	@SerializedName("episode")
	val episodesList: List<Episode> = listOf()

}