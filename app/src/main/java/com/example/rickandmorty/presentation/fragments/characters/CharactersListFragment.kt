package com.example.rickandmorty.presentation.fragments.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState

import com.example.rickandmorty.data.network.CharacterApi
import com.example.rickandmorty.databinding.FragmentCharactersListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class CharactersListFragment : Fragment() {

	private lateinit var binding: FragmentCharactersListBinding
	private val adapter = CharacterAdapter()


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


		val viewModel =
			ViewModelProvider(
				this,
				CharacterViewModelFactory(CharacterApi.api)
			)[CharactersViewModel::class.java]

		binding.swipeRefresh.setOnRefreshListener { adapter.refresh() }

		with(viewModel) {
			lifecycleScope.launch {
				viewModel.characters.collectLatest { pagingData ->
					adapter.submitData(pagingData)
				}
			}
			lifecycleScope.launch {
				viewModel.characters.collectLatest {
					binding.swipeRefresh.isRefreshing = false
				}

			}
		}

		adapter.withLoadStateHeaderAndFooter(
			header = CharacterLoadingStateAdapter(),
			footer = CharacterLoadingStateAdapter()
		)

		adapter.addLoadStateListener { state: CombinedLoadStates ->
			binding.charactersList.isVisible = state.refresh != LoadState.Loading
			binding.progress.isVisible = state.refresh == LoadState.Loading
		}

	}

}



