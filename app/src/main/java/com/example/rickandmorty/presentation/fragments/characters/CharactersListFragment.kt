package com.example.rickandmorty.presentation.fragments.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.data.network.CharacterApi
import com.example.rickandmorty.databinding.FragmentCharactersListBinding
import kotlinx.coroutines.launch


class CharactersListFragment : Fragment() {

    private lateinit var binding: FragmentCharactersListBinding
    var characterAdapter = CharacterAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharactersListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel =
            ViewModelProvider(
                this,
                CharacterViewModelFactory(CharacterApi.api)
            )[CharactersViewModel::class.java]

        binding.charactersList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = characterAdapter
        }

        lifecycleScope.launch {
            viewModel.characters.collect {
                characterAdapter.submitData(it)
            }
        }

    }


}



