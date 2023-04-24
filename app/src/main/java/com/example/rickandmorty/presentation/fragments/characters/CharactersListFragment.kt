package com.example.rickandmorty.presentation.fragments.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.example.rickandmorty.data.api.RetrofitInstance
import com.example.rickandmorty.databinding.FragmentCharactersListBinding
import com.example.rickandmorty.presentation.Navigator
import com.example.rickandmorty.presentation.fragments.adapters.CharactersListAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.example.rickandmorty.domain.model.character.Character


class CharactersListFragment : Fragment(), CharactersListAdapter.Listener {

	private lateinit var binding: FragmentCharactersListBinding
	private lateinit var viewModel: CharactersListViewModel
	private lateinit var navigator: Navigator
	private val adapter = CharactersListAdapter(this)
	private val viewModelDetail: CharacterDetailViewModel by activityViewModels()
	private var name = ""
	private var status = ""
	private var gender = ""

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setFragmentResultListener("requestKey") { _, bundle ->
			name = bundle.getString("name") ?: ""
			status = bundle.getString("status") ?: ""
			gender = bundle.getString("gender") ?: ""
			lifecycleScope.launch {
				viewModel.load(name, status, gender)
				viewModel.characterFlow.collectLatest(adapter::submitData)
			}
		}
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentCharactersListBinding.inflate(inflater)
		navigator = requireActivity() as Navigator
		viewModel =
			ViewModelProvider(
				this,
				CharacterViewModelFactory(RetrofitInstance.characterApi)
			)[CharactersListViewModel::class.java]
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.charactersList.adapter = adapter
		binding.btnFilter.setOnClickListener {
			navigator.replaceFragment(
				CharacterFilterFragment.newInstance(name, status, gender),
				"Filter"
			)
		}
		loadCharacters()
		swipeRefresh()
	}

	private fun swipeRefresh() {
		binding.swipeRefresh.setOnRefreshListener {
			lifecycleScope.launch {
				adapter.submitData(PagingData.empty())
				viewModel.characterFlow.collectLatest(adapter::submitData)
			}
			binding.swipeRefresh.isRefreshing = false
		}
	}

	private fun loadCharacters() {
		lifecycleScope.launch {
			viewModel.load(name, gender, status)
			viewModel.characterFlow.collectLatest(adapter::submitData)
		}
		adapter.addLoadStateListener {
			binding.charactersList.isVisible = it.refresh != LoadState.Loading
			binding.progress.isVisible = it.refresh == LoadState.Loading
		}
	}

	override fun onClick(character: Character) {
		viewModelDetail.onClickItemCharacter(character)
		navigator.replaceFragment(
			CharacterDetailFragment(viewModelDetail),
			"Character"
		)
	}

	companion object {

		@JvmStatic
		fun newInstance() = CharactersListFragment()
	}
}







