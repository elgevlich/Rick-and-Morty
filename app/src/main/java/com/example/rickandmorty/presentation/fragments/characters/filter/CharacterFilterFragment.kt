package com.example.rickandmorty.presentation.fragments.characters.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult

import com.example.rickandmorty.databinding.FragmentCharacterFilterBinding
import com.example.rickandmorty.presentation.Navigator


class CharacterFilterFragment : Fragment() {

	private lateinit var binding: FragmentCharacterFilterBinding


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentCharacterFilterBinding.inflate(inflater)
		return binding.root
	}


	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val navigator = requireActivity() as Navigator
		val callback = object : OnBackPressedCallback(true) {
			override fun handleOnBackPressed() {
				navigator.popUpToBackStack("Characters")
			}
		}
		activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)

		binding.btnMakeFilter.setOnClickListener {
			var status = ""
			var gender = ""
			if (binding.chipAlive.isChecked) status = "Alive"
			if (binding.chipDead.isChecked) status = "Dead"
			if (binding.chipUnknown.isChecked) status = "unknown"
			if (binding.chipMale.isChecked) gender = "Male"
			if (binding.chipFemale.isChecked) gender = "Female"
			if (binding.chipUnknownGender.isChecked) gender = "unknown"
			if (binding.chipGenderless.isChecked) gender = "Genderless"

			setFragmentResult(
				"requestKey", Bundle().apply {
					putString("gender", gender)
					putString("status", status)
				}
			)
			navigator.popUpToBackStack("Character")
		}
	}

	companion object {

		@JvmStatic
		fun newInstance() = CharacterFilterFragment()
	}
}
