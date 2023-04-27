package com.example.rickandmorty.presentation

import androidx.fragment.app.Fragment

interface Navigator {

	fun replaceFragment(fragment: Fragment, tagNext: String, tagCurrent: String)
	fun popUpToBackStack(tag: String)
	fun removeFragment(fragment: Fragment, tag: String)
}