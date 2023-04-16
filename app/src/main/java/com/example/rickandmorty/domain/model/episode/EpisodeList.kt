package com.example.rickandmorty.domain.model.episode

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EpisodeList(
	var results: List<Episode>
): Parcelable