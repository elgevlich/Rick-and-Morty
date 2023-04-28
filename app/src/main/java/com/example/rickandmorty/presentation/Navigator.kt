package com.example.rickandmorty.presentation

import androidx.fragment.app.Fragment

interface Navigator {

	fun replaceFragment(fragment: Fragment)
	fun popUpToBackStack()
	fun hideBottomNav()
	fun showBottomNav(fragmentTag: String)
}