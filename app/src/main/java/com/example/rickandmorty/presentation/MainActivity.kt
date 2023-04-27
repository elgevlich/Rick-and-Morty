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
		visibilityBottomNavigation("Characters")
		binding.contentLayout.bottomNavigation.setOnItemSelectedListener { item ->
			when (item.itemId) {
				R.id.characters -> {
					addFragment(CharactersListFragment.newInstance(), "Characters")
					true
				}
				R.id.locations -> {
					addFragment(LocationsListFragment.newInstance(), "Locations")
					true
				}
				R.id.episodes -> {
					addFragment(EpisodesListFragment.newInstance(), "Episodes")
					true
				}
				else -> false
			}
		}
	}


	private fun visibilityBottomNavigation(fragmentTag: String) {
		when (fragmentTag) {
			"Characters" -> {
				binding.toolbar.visibility = View.VISIBLE
				binding.toolbar.title = fragmentTag
				binding.contentLayout.bottomNavigation.visibility = View.VISIBLE
				binding.toolbar.navigationIcon = null
			}
			"Locations" -> {
				binding.toolbar.visibility = View.VISIBLE
				binding.toolbar.title = fragmentTag
				binding.contentLayout.bottomNavigation.visibility = View.VISIBLE
				binding.toolbar.navigationIcon = null
			}
			"Episodes" -> {
				binding.toolbar.visibility = View.VISIBLE
				binding.toolbar.title = fragmentTag
				binding.toolbar.navigationIcon = null
				binding.contentLayout.bottomNavigation.visibility = View.VISIBLE
			}
			else -> {
				binding.toolbar.visibility = View.GONE
				binding.contentLayout.bottomNavigation.visibility = View.GONE
			}
		}
	}

	private fun addFragment(fragment: Fragment, tag: String) {
		visibilityBottomNavigation(tag)
		supportFragmentManager
			.beginTransaction()
			.replace(R.id.container, fragment, "$fragment")
			.commit()
	}

	override fun replaceFragment(fragment: Fragment, tagNext: String, tagCurrent: String) {
		visibilityBottomNavigation(tagNext)
		supportFragmentManager
			.beginTransaction()
			.replace(R.id.container, fragment)
			.addToBackStack(tagCurrent)
			.commit()
	}

	override fun popUpToBackStack(tag: String) {
		visibilityBottomNavigation(tag)
		supportFragmentManager.popBackStack()
	}

	override fun removeFragment(fragment: Fragment, tag: String) {
		visibilityBottomNavigation(tag)
		supportFragmentManager
			.beginTransaction()
			.remove(fragment)
			.commit()
	}
}