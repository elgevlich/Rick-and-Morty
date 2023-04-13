package com.example.rickandmorty.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Episode(
	var id: Int,
	var name: String,
	var episode: String,
	var air_date: String,
	var characters: List<String>
) : Parcelable
