package com.example.rickandmorty.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.presentation.fragments.characters.CharactersListFragment
import com.example.rickandmorty.presentation.fragments.episodes.EpisodesListFragment
import com.example.rickandmorty.presentation.fragments.locations.LocationListFragment
import com.example.rickandmorty.presentation.fragments.locations.LocationViewModel


class MainActivity : AppCompatActivity() {

	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		initBottomNav()
	}



	private fun initBottomNav() {
		replaceFragment(CharactersListFragment())
		binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
			when (item.itemId) {
				R.id.characters -> replaceFragment(CharactersListFragment())
				R.id.locations -> replaceFragment(LocationListFragment())
				R.id.episodes -> replaceFragment(EpisodesListFragment())
			}
			true
		}
	}



	private fun replaceFragment(fragment: Fragment) {
		supportFragmentManager
			.beginTransaction()
			.replace(R.id.fragment_container, fragment)
			.commit()
	}
}