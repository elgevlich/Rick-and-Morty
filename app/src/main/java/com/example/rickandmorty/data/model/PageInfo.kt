package com.example.rickandmorty.data.model

data class PageInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)