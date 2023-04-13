package com.example.rickandmorty.presentation.fragments.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

import com.example.rickandmorty.R


import com.example.rickandmorty.data.network.RepositoryEpisodes
import com.example.rickandmorty.databinding.FragmentEpisodesListBinding
import com.example.rickandmorty.presentation.fragments.adapters.EpisodeAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class EpisodesListFragment : Fragment() {

	private lateinit var binding: FragmentEpisodesListBinding
	private val adapter = EpisodeAdapter()

	private val viewModel: EpisodeViewModel by activityViewModels {
		EpisodeViewModelFactory(
			RepositoryEpisodes()
		)
	}


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentEpisodesListBinding.inflate(inflater)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		showBottomNav()
		binding.charactersList.adapter = adapter

		getNameSearchView()

		viewModel.isFilter.observe(viewLifecycleOwner) {
			binding.txtReset.visibility = if (it) View.VISIBLE else View.INVISIBLE
		}

		binding.txtReset.setOnClickListener {
			viewModel.getEpisodes(1)
			viewModel.filterValue.value = arrayOf(0, 0)
		}


		viewModel.getEpisodes(1)

		viewModel.listEpisodes.observe(viewLifecycleOwner) {
			adapter.setEpisodes(it)
		}

		getNameSearchView()

	}

	private fun getNameSearchView() {

		binding.episodesSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

			override fun onQueryTextSubmit(query: String?): Boolean {
				viewModel.getEpisodesByName(query.toString())
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



