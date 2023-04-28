package com.example.rickandmorty.presentation


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.presentation.fragments.characters.list.CharactersListFragment
import com.example.rickandmorty.presentation.fragments.episodes.list.EpisodesListFragment
import com.example.rickandmorty.presentation.fragments.locations.list.LocationsListFragment


class MainActivity : FragmentActivity(), Navigator {

	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		showBottomNav("Characters")
		binding.contentLayout.bottomNavigation.setOnItemSelectedListener { item ->
			when (item.itemId) {
				R.id.characters -> {
					addFragment(CharactersListFragment.newInstance())
					true
				}
				R.id.locations -> {
					addFragment(LocationsListFragment.newInstance())
					true
				}
				R.id.episodes -> {
					addFragment(EpisodesListFragment.newInstance())
					true
				}
				else -> false
			}
		}
	}

	private fun addFragment(fragment: Fragment) {
		supportFragmentManager
			.beginTransaction()
			.replace(R.id.container, fragment, "$fragment")
			.commit()
	}

	override fun hideBottomNav() {
		binding.toolbar.visibility = View.GONE
		binding.contentLayout.bottomNavigation.visibility = View.GONE
	}

	override fun showBottomNav(fragmentTag: String) {
		binding.toolbar.visibility = View.VISIBLE
		binding.toolbar.title = fragmentTag
		binding.contentLayout.bottomNavigation.visibility = View.VISIBLE
	}

	override fun replaceFragment(fragment: Fragment) {
		supportFragmentManager
			.beginTransaction()
			.replace(R.id.container, fragment)
			.addToBackStack(null)
			.commit()
	}

	override fun popUpToBackStack() {
		supportFragmentManager.popBackStack()
	}

}