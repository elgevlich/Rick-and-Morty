package com.example.rickandmorty.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterListResponse(
 val results: List<Character>
) : Parcelable