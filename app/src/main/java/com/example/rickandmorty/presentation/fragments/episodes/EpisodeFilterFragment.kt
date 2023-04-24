package com.example.rickandmorty.presentation.fragments.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.rickandmorty.databinding.FragmentEpisodeFilterBinding
import com.example.rickandmorty.presentation.Navigator

class EpisodeFilterFragment : Fragment() {

	private lateinit var binding: FragmentEpisodeFilterBinding

	var name = ""
	var episode = ""

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
		val callback = object : OnBackPressedCallback(true) {
			override fun handleOnBackPressed() {
				navigator.popUpToBackStack("Episodes")
			}
		}
		activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)

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
			navigator.popUpToBackStack("Episodes")
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