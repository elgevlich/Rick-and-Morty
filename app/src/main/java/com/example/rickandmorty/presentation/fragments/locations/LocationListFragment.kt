package com.example.rickandmorty.presentation.fragments.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.rickandmorty.R
import com.example.rickandmorty.data.network.RepositoryLocations
import com.example.rickandmorty.databinding.FragmentLocationListBinding
import com.example.rickandmorty.presentation.fragments.adapters.LocationAdapter

import com.google.android.material.bottomnavigation.BottomNavigationView

class LocationListFragment : Fragment() {

	private lateinit var binding: FragmentLocationListBinding
	private val adapter = LocationAdapter()

	private val viewModel: LocationViewModel by activityViewModels {
		LocationViewModelFactory(
			RepositoryLocations()
		)
	}


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentLocationListBinding.inflate(inflater)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		showBottomNav()
		binding.locationList.adapter = adapter


		getNameSearchView()

		viewModel.isFilter.observe(viewLifecycleOwner) {
			binding.txtReset.visibility = if (it) View.VISIBLE else View.INVISIBLE
		}

		binding.txtReset.setOnClickListener {
			viewModel.getLocations(1)
			viewModel.filterValue.value = arrayOf(0, 0)
		}


		viewModel.getLocations(1)

		viewModel.listLocations.observe(viewLifecycleOwner) {
			adapter.setLocations(it)
		}

		getNameSearchView()

	}

	private fun getNameSearchView() {

		binding.locationSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

			override fun onQueryTextSubmit(query: String?): Boolean {
				viewModel.getLocationsByName(query.toString())
				return true
			}

			override fun onQueryTextChange(newText: String?): Boolean {
				return true
			}
		})
	}

	private fun showBottomNav(){
		val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
		bottomNavigationView.visibility = View.VISIBLE
	}


}