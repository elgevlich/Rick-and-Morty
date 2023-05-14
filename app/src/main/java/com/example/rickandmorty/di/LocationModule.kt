package com.example.rickandmorty.di

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.api.LocationApi
import com.example.rickandmorty.data.api.RetrofitInstance
import com.example.rickandmorty.data.database.LocationDatabase
import com.example.rickandmorty.data.database.dao.LocationDao
import com.example.rickandmorty.data.repository.LocationRepositoryImpl
import com.example.rickandmorty.domain.repository.LocationRepository
import com.example.rickandmorty.presentation.fragments.locations.list.LocationsListViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface LocationModule {

	@Binds
	fun bindLocationRepository(impl: LocationRepositoryImpl): LocationRepository

	@Binds
	@IntoMap
	@ViewModelKey(LocationsListViewModel::class)
	fun bindLocationViewModel(viewModel: LocationsListViewModel): ViewModel

	companion object {

		@Provides
		fun provideApiService(): LocationApi {
			return RetrofitInstance.locationApi
		}

		@Provides
		fun provideLocationDao(application: Application): LocationDao {
			return LocationDatabase.getMainDatabase(application).getLocationDao()
		}
	}
}