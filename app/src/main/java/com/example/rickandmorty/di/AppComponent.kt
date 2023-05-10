package com.example.rickandmorty.di

import android.app.Application
import com.example.rickandmorty.app.App
import com.example.rickandmorty.presentation.MainActivity
import com.example.rickandmorty.presentation.fragments.characters.CharacterFilterFragment
import com.example.rickandmorty.presentation.fragments.characters.details.CharacterDetailFragment
import com.example.rickandmorty.presentation.fragments.characters.list.CharactersListFragment
import com.example.rickandmorty.presentation.fragments.episodes.EpisodeFilterFragment
import com.example.rickandmorty.presentation.fragments.episodes.details.EpisodeDetailFragment
import com.example.rickandmorty.presentation.fragments.episodes.list.EpisodesListFragment
import com.example.rickandmorty.presentation.fragments.locations.LocationFilterFragment
import com.example.rickandmorty.presentation.fragments.locations.details.LocationDetailFragment
import com.example.rickandmorty.presentation.fragments.locations.list.LocationsListFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [CharacterModule::class, EpisodeModule::class, LocationModule::class])
interface AppComponent {

	fun inject(activity: MainActivity)
	fun inject(fragment: LocationFilterFragment)
	fun inject(fragment: EpisodeFilterFragment)
	fun inject(fragment: CharacterFilterFragment)
	fun inject(fragment: LocationsListFragment)
	fun inject(fragment: EpisodesListFragment)
	fun inject(fragment: CharactersListFragment)
	fun inject(fragment: CharacterDetailFragment)
	fun inject(fragment: EpisodeDetailFragment)
	fun inject(fragment: LocationDetailFragment)
	fun inject(application: App)

	@Component.Factory
	interface Factory {

		fun create(
			@BindsInstance application: Application
		): AppComponent
	}
}