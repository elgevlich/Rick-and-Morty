package com.example.rickandmorty.data.model

import android.location.Location
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Character (
	var id : Int,
	var name: String,
	var status : String,
	var species: String,
	var gender: String,
	var origin : Location,
	var location : Location,
	var image : String,
	var episode : List<String>
): Parcelable