package com.example.rickandmorty.presentation.fragments.episodes.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.rickandmorty.databinding.FragmentEpisodeDetailBinding
import com.example.rickandmorty.presentation.Navigator

class EpisodeDetailFragment : Fragment() {

	private lateinit var binding: FragmentEpisodeDetailBinding

	private val NAME_KEY = "nameKey"
	private val EPISODE_KEY = "episodeKey"
	private val AIRDATE_KEY = "airDateKey"

	private var name: String? = null
	private var episode: String? = null
	private var airDate: String? = null

	private var episodeName: TextView? = null
	private var episodeCode: TextView? = null
	private var episodeAirDate: TextView? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		if (arguments != null) {
			name = requireArguments().getString(NAME_KEY)
			episode = requireArguments().getString(EPISODE_KEY)
			airDate = requireArguments().getString(AIRDATE_KEY)
		}
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentEpisodeDetailBinding.inflate(inflater)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initViews()
		val navigator = requireActivity() as Navigator
		binding.backButton.setOnClickListener {
			navigator.popUpToBackStack("Episodes")
		}
	}

	private fun initViews() {
		episodeName = binding.episodeName
		episodeCode = binding.episodeNumber
		episodeAirDate = binding.episodeAirDate

		episodeName!!.text = name
		episodeCode!!.text = episode
		episodeAirDate!!.text = airDate
	}

	companion object {

		private const val NAME_KEY = "nameKey"
		private const val EPISODE_KEY = "episodeKey"
		private const val AIRDATE_KEY = "airDateKey"
		fun newInstance(
			name: String?,
			episode: String?,
			airDate: String?,
		): EpisodeDetailFragment {
			val detailsFragment = EpisodeDetailFragment()
			val bundle = Bundle()
			bundle.putString(NAME_KEY, name)
			bundle.putString(EPISODE_KEY, episode)
			bundle.putString(AIRDATE_KEY, airDate)
			detailsFragment.arguments = bundle
			return detailsFragment
		}
	}
}