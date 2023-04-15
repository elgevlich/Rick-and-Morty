package com.example.rickandmorty.presentation.fragments.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible


import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.example.rickandmorty.data.model.Character


import com.example.rickandmorty.data.network.RetrofitInstance


import com.example.rickandmorty.databinding.FragmentCharactersListBinding
import com.example.rickandmorty.presentation.Navigator
import com.example.rickandmorty.presentation.fragments.adapters.CharacterAdapter
import com.example.rickandmorty.presentation.fragments.adapters.CharacterLoadingStateAdapter
import com.example.rickandmorty.presentation.fragments.characters.filter.CharacterFilterFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class CharactersListFragment : Fragment(), CharacterAdapter.Listener {

    private lateinit var binding: FragmentCharactersListBinding
    private lateinit var viewModel: CharacterViewModel
    private lateinit var navigator: Navigator
    private val adapter = CharacterAdapter(this)
    private var isPull = false
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
            )[CharacterViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.charactersList.adapter = adapter

        binding.btnFilter.setOnClickListener {
            navigator.replaceFragment(CharacterFilterFragment.newInstance(), "Filter")
        }



        binding.swipeRefresh.setOnRefreshListener {
            isPull = true
            adapter.refresh()
        }

        lifecycleScope.launch {
            viewModel.load(name, status, gender)
            viewModel.characterFlow.collectLatest(adapter::submitData)
        }


        adapter.withLoadStateHeaderAndFooter(
            header = CharacterLoadingStateAdapter(),
            footer = CharacterLoadingStateAdapter()
        )

        adapter.addLoadStateListener { state: CombinedLoadStates ->
            if (!isPull) {
                binding.charactersList.isVisible = state.refresh != LoadState.Loading
                binding.progress.isVisible = state.refresh == LoadState.Loading
            }
        }

    }

    companion object {

        @JvmStatic
        fun newInstance() = CharactersListFragment()

    }

    override fun onClick(character: Character) {
        viewModel.dataCharacter.value = character
        navigator.replaceFragment(CharacterDetailFragment.newInstance(), "Character Details")
    }

}









