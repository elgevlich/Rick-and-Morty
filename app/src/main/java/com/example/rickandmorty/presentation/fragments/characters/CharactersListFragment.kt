package com.example.rickandmorty.presentation.fragments.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView

import androidx.core.view.isVisible

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState


import com.example.rickandmorty.data.network.Repository
import com.example.rickandmorty.databinding.FragmentCharactersListBinding

class CharactersListFragment : Fragment() {

    private lateinit var binding: FragmentCharactersListBinding
    private val adapter = CharacterAdapter()

    private val viewModel: CharactersViewModel by activityViewModels {
        CharacterViewModelFactory(
            Repository()
        )
    }

//    private var isPull = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.charactersList.adapter = adapter

//        binding.swipeRefresh.setOnRefreshListener {
//            isPull = true
//            adapter.refresh()
//        }


        viewModel.getCharacters(1)

        viewModel.listCharactersInEpisode.observe(viewLifecycleOwner) {
            adapter.setCharacters(it)
        }

        getNameSearchView()


//        adapter.withLoadStateHeaderAndFooter(
//            header = CharacterLoadingStateAdapter(),
//            footer = CharacterLoadingStateAdapter()
//        )
//
//        adapter.addLoadStateListener { state: CombinedLoadStates ->
//            if (!isPull) {
//                binding.charactersList.isVisible = state.refresh != LoadState.Loading
//                binding.progress.isVisible = state.refresh == LoadState.Loading
//            }
//        }


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


}



