package com.example.rickandmorty.domain.model.character

import android.os.Parcelable
import com.example.rickandmorty.domain.model.location.LocationData
import kotlinx.parcelize.Parcelize


@Parcelize
data class Character (
	val id : Int,
	val name: String,
	val status : String,
	val species: String,
	val gender: String,
	val origin : LocationData,
	val location : LocationData,
	val image : String,
	val episode : List<String>
): Parcelable