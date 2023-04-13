package com.example.rickandmorty.presentation.fragments.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import com.example.rickandmorty.data.model.Character

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels

import com.example.rickandmorty.R


import com.example.rickandmorty.data.network.RepositoryCharacters
import com.example.rickandmorty.databinding.FragmentCharactersListBinding
import com.example.rickandmorty.presentation.fragments.adapters.CharacterAdapter
import com.example.rickandmorty.presentation.fragments.characters.filter.CharacterFilterFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class CharactersListFragment : Fragment(), CharacterAdapter.Listener {

	private lateinit var binding: FragmentCharactersListBinding
	private val adapter = CharacterAdapter(this)

	private val viewModel: CharacterViewModel by activityViewModels {
		CharacterViewModelFactory(
			RepositoryCharacters()
		)
	}


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentCharactersListBinding.inflate(inflater)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		showBottomNav()
		binding.charactersList.adapter = adapter


		binding.btnFilter.setOnClickListener {

			val filterFragment = CharacterFilterFragment()
			val transaction = requireActivity().supportFragmentManager
				.beginTransaction()
			transaction.replace(R.id.fragment_container, filterFragment)
			transaction.commit()

		}

		getNameSearchView()

		viewModel.isFilter.observe(viewLifecycleOwner) {
			binding.txtReset.visibility = if (it) View.VISIBLE else View.INVISIBLE
		}

		binding.txtReset.setOnClickListener {
			viewModel.getCharacters(1)
			viewModel.filterValue.value = arrayOf(0, 0)
		}


		viewModel.getCharacters(1)

		viewModel.listCharactersInEpisode.observe(viewLifecycleOwner) {
			adapter.setCharacters(it)
		}

		getNameSearchView()


	}

	override fun onClick(character: Character) {
		viewModel.dataCharacter.value = character
		val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
		fragmentManager
			.beginTransaction()
			.replace(R.id.fragment_container, CharacterDetailsFragment::class.java.newInstance())
			.addToBackStack("characters")
			.commit()
	}

	private fun getNameSearchView() {

		binding.characterSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

			override fun onQueryTextSubmit(query: String?): Boolean {
				viewModel.getByName(query.toString())
				return true
			}

			override fun onQueryTextChange(newText: String?): Boolean {
				return true
			}
		})
	}

	private fun showBottomNav() {
		val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
		bottomNavigationView.visibility = View.VISIBLE
	}


}



