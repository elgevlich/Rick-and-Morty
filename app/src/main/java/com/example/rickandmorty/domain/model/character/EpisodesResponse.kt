package com.example.rickandmorty.domain.model.character

import android.os.Parcelable
import com.example.rickandmorty.domain.model.episode.EpisodeList
import kotlinx.parcelize.Parcelize

@Parcelize
data class EpisodesResponse (
    var episode: EpisodeList
): Parcelable