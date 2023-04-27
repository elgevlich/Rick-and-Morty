package com.example.rickandmorty.presentation.fragments.episodes.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.data.api.CharacterApi
import com.example.rickandmorty.data.api.RetrofitInstance.characterApi
import com.example.rickandmorty.databinding.FragmentEpisodeDetailBinding
import com.example.rickandmorty.domain.model.character.Character
import com.example.rickandmorty.domain.model.episode.Episode
import com.example.rickandmorty.presentation.Navigator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class EpisodeDetailFragment : Fragment() {

	private lateinit var binding: FragmentEpisodeDetailBinding

//	private val NAME_KEY = "nameKey"
//	private val EPISODE_KEY = "episodeKey"
//	private val AIRDATE_KEY = "airDateKey"
//
//	private var name: String? = null
//	private var episode: String? = null
//	private var airDate: String? = null

	private var episodeName: TextView? = null
	private var episodeCode: TextView? = null
	private var episodeAirDate: TextView? = null
	var rvListOfCharacters: RecyclerView? = null
	var api: CharacterApi? = null
	private lateinit var viewModelDetail: EpisodeDetailViewModel
	var compositeDisposable = CompositeDisposable()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentEpisodeDetailBinding.inflate(inflater)
		viewModelDetail = ViewModelProvider(this)[EpisodeDetailViewModel::class.java]
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
//		initViews()

		rvListOfCharacters = binding.charactersList
		api = characterApi
		rvListOfCharacters!!.setHasFixedSize(true)

		episodeName = binding.episodeName
		episodeCode = binding.episodeNumber
		episodeAirDate = binding.episodeAirDate

		val observer = Observer<Episode> { episode1: Episode? ->
			assert(episode1 != null)
			episodeName!!.text = episode1!!.name
			episodeCode!!.text = episode1.episode
			episodeAirDate!!.text = episode1.air_date
		}

		viewModelDetail.getSelectedItemEpisode()?.observe(viewLifecycleOwner, observer)
		viewModelDetail.getCharacters()
		fetchData()
		viewModelDetail.clearListOfCharacters()


		val navigator = requireActivity() as Navigator
		binding.backButton.setOnClickListener {
			navigator.popUpToBackStack("Episodes")
		}
	}

	private fun fetchData() {
		viewModelDetail.charactersIds?.let {
			api!!.getListOfCharactersForDetails(it)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe({ posts: List<Character> ->
					displayData(
						posts
					)
				}
				) { throwable: Throwable ->
					Log.d(
						"tag",
						throwable.toString()
					)
				}
		}?.let {
			compositeDisposable.add(
				it
			)
		}
	}

	private fun displayData(posts: List<Character>) {
		val adapter = EpisodeDetailsAdapter(requireContext(), posts)
		rvListOfCharacters!!.adapter = adapter
	}

	override fun onStop() {
		compositeDisposable.clear()
		super.onStop()
	}

	companion object {

		@JvmStatic
		fun newInstance(viewModelDetail: EpisodeDetailViewModel) = EpisodeDetailFragment()
	}

//	private fun initViews() {
//		episodeName = binding.episodeName
//		episodeCode = binding.episodeNumber
//		episodeAirDate = binding.episodeAirDate
//
//		episodeName!!.text = name
//		episodeCode!!.text = episode
//		episodeAirDate!!.text = airDate
//	}

}