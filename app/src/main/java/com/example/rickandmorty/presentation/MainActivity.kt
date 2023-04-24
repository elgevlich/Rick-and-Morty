package com.example.rickandmorty.presentation


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.presentation.fragments.characters.CharactersListFragment
import com.example.rickandmorty.presentation.fragments.episodes.EpisodesListFragment
import com.example.rickandmorty.presentation.fragments.locations.LocationsListFragment


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
		binding.toolbar.setNavigationOnClickListener {
			popUpToBackStack("Characters")
		}
	}


	private fun visibilityBottomNavigation(fragmentTag: String) {
		when (fragmentTag) {
			"Characters" -> {
				binding.toolbar.title = fragmentTag
				binding.contentLayout.bottomNavigation.visibility = View.VISIBLE
				binding.toolbar.navigationIcon = null
			}
			"Locations" -> {
				binding.toolbar.title = fragmentTag
				binding.contentLayout.bottomNavigation.visibility = View.VISIBLE
				binding.toolbar.navigationIcon = null
			}
			"Episodes" -> {
				binding.toolbar.title = fragmentTag
				binding.toolbar.navigationIcon = null
				binding.contentLayout.bottomNavigation.visibility = View.VISIBLE
			}
			else -> {
				binding.toolbar.title = fragmentTag
				binding.contentLayout.bottomNavigation.visibility = View.GONE
				binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
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

	override fun replaceFragment(fragment: Fragment, tag: String) {
		visibilityBottomNavigation(tag)
		supportFragmentManager
			.beginTransaction()
			.replace(R.id.container, fragment)
			.addToBackStack(null)
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