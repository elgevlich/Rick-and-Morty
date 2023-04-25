package com.example.rickandmorty.presentation.fragments.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.rickandmorty.databinding.FragmentLocationFilterBinding
import com.example.rickandmorty.presentation.Navigator

class LocationFilterFragment : Fragment() {

	private lateinit var binding: FragmentLocationFilterBinding
	var name = ""
	var type = ""
	var dimension = ""

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		name = arguments?.getString("name") ?: ""
		type = arguments?.getString("type") ?: ""
		dimension = arguments?.getString("dimension") ?: ""
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentLocationFilterBinding.inflate(inflater)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val navigator = requireActivity() as Navigator

		binding.backButton.setOnClickListener {
			navigator.popUpToBackStack("Locations")
		}

		if (name.isNotEmpty()) binding.nameLocationSearch.text = name
		if (type.isNotEmpty()) binding.typeLocationSearch.text = type
		if (dimension.isNotEmpty()) binding.dimensionLocationSearch.text = type

		binding.btnMakeFilter.setOnClickListener {
			name = ""
			type = ""
			dimension = ""
			name = binding.searchByName.text.toString()
			type = binding.searchByType.text.toString()
			dimension = binding.searchByDimension.text.toString()

			setFragmentResult(
				"requestKey", Bundle().apply {
					putString("name", name)
					putString("type", type)
					putString("dimension", dimension)
				}
			)
			navigator.popUpToBackStack("Locations")
		}
	}

	companion object {

		@JvmStatic
		fun newInstance(name: String, type: String, dimension: String): LocationFilterFragment {
			return LocationFilterFragment().apply {
				arguments = Bundle().apply {
					putString("name", name)
					putString("type", type)
					putString("dimension", dimension)
				}
			}
		}
	}
}