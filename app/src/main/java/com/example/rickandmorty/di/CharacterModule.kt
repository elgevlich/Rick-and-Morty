package com.example.rickandmorty.di

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.api.CharacterApi
import com.example.rickandmorty.data.api.RetrofitInstance
import com.example.rickandmorty.data.database.dao.CharacterDao
import com.example.rickandmorty.data.database.entity.CharacterDatabase
import com.example.rickandmorty.data.repository.CharacterRepositoryImpl
import com.example.rickandmorty.domain.repository.CharacterRepository
import com.example.rickandmorty.presentation.fragments.characters.list.CharactersListViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface CharacterModule {

	@Binds
	fun bindCharacterRepository(repository: CharacterRepositoryImpl): CharacterRepository

	@Binds
	@IntoMap
	@ViewModelKey(CharactersListViewModel::class)
	fun bindCharacterViewModel(viewModel: CharactersListViewModel): ViewModel

	companion object{
		@Provides
		fun provideApiService(): CharacterApi {
			return RetrofitInstance.characterApi
		}

		@Provides
		fun provideCharacterDao(application: Application): CharacterDao {
			return CharacterDatabase.getMainDatabase(application).getCharacterDao()
		}
	}
}