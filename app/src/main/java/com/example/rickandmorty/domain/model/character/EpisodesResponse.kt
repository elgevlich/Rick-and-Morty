package com.example.rickandmorty.domain.model.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EpisodesResponse (
    var episode: EpisodesResponse
): Parcelable