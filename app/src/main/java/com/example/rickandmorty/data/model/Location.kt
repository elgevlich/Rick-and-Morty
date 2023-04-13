package com.example.rickandmorty.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
	var id: Int,
	var name: String,
	var type: String,
	var dimension: String,
	var residents: List<String>
) : Parcelable