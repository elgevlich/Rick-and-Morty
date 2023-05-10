package com.example.rickandmorty.data.api.response.episode

import com.example.rickandmorty.data.api.response.PageInfoResponse

data class EpisodeResponse(
	val info: PageInfoResponse,
	val results: List<EpisodeResultResponse>
)