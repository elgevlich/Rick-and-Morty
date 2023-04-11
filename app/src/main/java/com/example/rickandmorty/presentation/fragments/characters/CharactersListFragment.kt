package com.example.rickandmorty.presentation.fragments.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.databinding.FragmentCharactersListBinding

class CharactersListFragment : Fragment() {

	private lateinit var binding: FragmentCharactersListBinding
	private lateinit var adapter: CharactersAdapter


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = FragmentCharactersListBinding.inflate(inflater)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.charactersList.adapter = adapter

		val viewModel = ViewModelProvider(requireActivity())[CharactersViewModel::class.java]
		viewModel.getCharacters()

		viewModel.charactersList.observe(viewLifecycleOwner) {
			adapter.setCharacter(it)
		}
	}
}

