package com.example.rickandmorty.di

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.api.EpisodeApi
import com.example.rickandmorty.data.api.RetrofitInstance
import com.example.rickandmorty.data.database.dao.EpisodeDao
import com.example.rickandmorty.data.database.entity.EpisodeDatabase
import com.example.rickandmorty.data.repository.EpisodeRepositoryImpl
import com.example.rickandmorty.domain.repository.EpisodeRepository
import com.example.rickandmorty.presentation.fragments.episodes.list.EpisodesListViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface EpisodeModule {

	@Binds
	fun bindEpisodeRepository(impl: EpisodeRepositoryImpl): EpisodeRepository

	@Binds
	@IntoMap
	@ViewModelKey(EpisodesListViewModel::class)
	fun bindEpisodeViewModel(viewModel: EpisodesListViewModel): ViewModel


	companion object {
		@Provides
		fun provideApiService(): EpisodeApi {
			return RetrofitInstance.episodeApi
		}

		@Provides
		fun provideEpisodeDao(application: Application): EpisodeDao {
			return EpisodeDatabase.getMainDatabase(application).getEpisodeDao()
		}
	}
}