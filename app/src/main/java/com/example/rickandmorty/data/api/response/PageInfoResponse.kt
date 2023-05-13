package com.example.rickandmorty.data.api.response

data class PageInfoResponse(
	val count: Int?,
	val next: String?,
	val pages: Int?,
	val prev: String?
)