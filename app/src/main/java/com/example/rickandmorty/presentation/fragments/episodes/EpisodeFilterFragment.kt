package com.example.rickandmorty.presentation.fragments.episodes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.rickandmorty.app.App
import com.example.rickandmorty.databinding.FragmentEpisodeFilterBinding
import com.example.rickandmorty.presentation.Navigator

class EpisodeFilterFragment : Fragment() {

	private lateinit var binding: FragmentEpisodeFilterBinding

	var name = ""
	var episode = ""

	private val component by lazy {
		(requireActivity().application as App).component
	}

	override fun onAttach(context: Context) {
		component.inject(this)
		super.onAttach(context)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		name = arguments?.getString("name") ?: ""
		episode = arguments?.getString("episode") ?: ""
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentEpisodeFilterBinding.inflate(inflater)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val navigator = requireActivity() as Navigator
		navigator.hideBottomNav()
		binding.backButton.setOnClickListener {
			navigator.popUpToBackStack()
		}

		if (name.isNotEmpty()) binding.searchByName.setText(name)
		if (episode.isNotEmpty()) binding.searchByCode.setText(episode)

		binding.btnMakeFilter.setOnClickListener {
			name = ""
			episode = ""
			name = binding.searchByName.text.toString()
			episode = binding.searchByCode.text.toString()

			setFragmentResult(
				"requestKey", Bundle().apply {
					putString("name", name)
					putString("episode", episode)
				}
			)
			navigator.popUpToBackStack()
		}
	}

	companion object {

		@JvmStatic
		fun newInstance(name: String, episode: String): EpisodeFilterFragment {
			return EpisodeFilterFragment().apply {
				arguments = Bundle().apply {
					putString("name", name)
					putString("episode", episode)
				}
			}
		}
	}
}