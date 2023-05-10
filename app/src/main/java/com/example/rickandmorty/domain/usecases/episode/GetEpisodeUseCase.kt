package com.example.rickandmorty.domain.usecases.episode

import android.app.Application
import com.example.rickandmorty.data.pagingSource.EpisodePagingSource
import com.example.rickandmorty.data.repository.EpisodeRepositoryImpl
import javax.inject.Inject

class GetEpisodeUseCase @Inject constructor(
	private val repository: EpisodeRepositoryImpl,
	private val application: Application
) {

	fun getEpisodes(name: String, episode: String): EpisodePagingSource {
		return EpisodePagingSource(repository, application, name, episode)
	}
}