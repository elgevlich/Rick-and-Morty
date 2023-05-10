package com.example.rickandmorty.presentation.fragments.episodes.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.rickandmorty.databinding.FragmentEpisodeDetailsBinding
import com.example.rickandmorty.domain.model.character.Character
import com.example.rickandmorty.presentation.Navigator
import com.example.rickandmorty.presentation.fragments.characters.details.CharacterDetailFragment
import com.example.rickandmorty.presentation.fragments.characters.details.CharacterDetailViewModel
import com.example.rickandmorty.presentation.fragments.locations.details.LocationDetailsAdapter


class EpisodeDetailFragment(private val episodeViewModel: EpisodeDetailViewModel) : Fragment(),
	LocationDetailsAdapter.OnClickListener {

	private lateinit var binding: FragmentEpisodeDetailsBinding
	private val detailCharacterViewModel: CharacterDetailViewModel by activityViewModels()
	private lateinit var navigator: Navigator
	lateinit var adapter: LocationDetailsAdapter

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentEpisodeDetailsBinding.inflate(inflater)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val navigator = requireActivity() as Navigator
		navigator.hideBottomNav()
		episodeViewModel.selectedItemEpisode.observe(viewLifecycleOwner) {
			binding.episodeName.text = it.name
			binding.episodeNumber.text = it.episode
			binding.episodeAirDate.text = it.air_date
		}
		binding.backButton.setOnClickListener {
			navigator.popUpToBackStack()
			episodeViewModel.clearListOfCharacters()
		}
		episodeViewModel.getCharacters()
		episodeViewModel.fetchData()
		episodeViewModel.responseCharacters.observe(viewLifecycleOwner) {
			adapter = LocationDetailsAdapter(requireContext(), it, this)
			binding.charactersList.adapter = adapter
		}
	}

	override fun onClick(character: Character?) {
		detailCharacterViewModel.onClickItemCharacter(character)
		navigator = requireActivity() as Navigator
		navigator.replaceFragment(
			CharacterDetailFragment(detailCharacterViewModel),
		)
	}
}