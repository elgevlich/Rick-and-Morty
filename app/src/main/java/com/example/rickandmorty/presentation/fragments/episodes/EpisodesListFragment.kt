package com.example.rickandmorty.presentation.fragments.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.example.rickandmorty.data.api.RetrofitInstance
import com.example.rickandmorty.databinding.FragmentEpisodesListBinding
import com.example.rickandmorty.domain.model.episode.Episode
import com.example.rickandmorty.presentation.Navigator
import com.example.rickandmorty.presentation.fragments.adapters.EpisodeAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class EpisodesListFragment : Fragment(), EpisodeAdapter.Listener {

	private lateinit var binding: FragmentEpisodesListBinding
	private val adapter = EpisodeAdapter(this)
	private lateinit var viewModel: EpisodesListViewModel
	private lateinit var navigator: Navigator

	private var name = ""
	private var episode = ""

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setFragmentResultListener("requestKey") { _, bundle ->
			name = bundle.getString("name") ?: ""
			episode = bundle.getString("episode") ?: ""
			lifecycleScope.launch {
				viewModel.load(name, episode)
				viewModel.episodeFlow.collectLatest(adapter::submitData)
			}
		}
	}


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentEpisodesListBinding.inflate(inflater)
		navigator = requireActivity() as Navigator
		viewModel =
			ViewModelProvider(
				this,
				EpisodeViewModelFactory(RetrofitInstance.episodeApi)
			)[EpisodesListViewModel::class.java]
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.episodeList.adapter = adapter
		binding.btnFilter.setOnClickListener {
			navigator.replaceFragment(
				EpisodeFilterFragment.newInstance(name, episode),
				"Filter"
			)
		}
		loadEpisodes()
		swipeRefresh()
	}

	private fun swipeRefresh() {
		binding.swipeRefresh.setOnRefreshListener {
			lifecycleScope.launch {
				adapter.submitData(PagingData.empty())
				viewModel.episodeFlow.collectLatest(adapter::submitData)
			}
			binding.swipeRefresh.isRefreshing = false
		}
	}

	private fun loadEpisodes() {
		lifecycleScope.launch {
			viewModel.load(name, episode)
			viewModel.episodeFlow.collectLatest(adapter::submitData)
		}
		adapter.addLoadStateListener {
			binding.episodeList.isVisible = it.refresh != LoadState.Loading
			binding.progress.isVisible = it.refresh == LoadState.Loading
		}
	}

	override fun onClick(episode: Episode) {
		viewModel.dataEpisode.value = episode
		navigator.replaceFragment(
			EpisodeDetailFragment.newInstance(
				episode.name,
				episode.episode,
				episode.air_date,
			),
			"Episode"
		)
	}

	companion object {

		@JvmStatic
		fun newInstance() = EpisodesListFragment()
	}
}



