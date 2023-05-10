package com.example.rickandmorty.domain.model.episode

import com.example.rickandmorty.domain.model.PageInfo


data class Episode (
	val info: PageInfo,
	val results: List<EpisodeResult>
)
