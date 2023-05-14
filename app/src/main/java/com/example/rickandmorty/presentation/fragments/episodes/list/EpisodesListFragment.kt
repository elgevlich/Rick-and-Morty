package com.example.rickandmorty.presentation.fragments.episodes.list

import android.content.Context
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
import com.example.rickandmorty.app.App
import com.example.rickandmorty.databinding.FragmentEpisodesListBinding
import com.example.rickandmorty.di.ViewModelFactory
import com.example.rickandmorty.domain.model.episode.EpisodeResult
import com.example.rickandmorty.presentation.Navigator
import com.example.rickandmorty.presentation.fragments.episodes.EpisodeFilterFragment
import com.example.rickandmorty.presentation.fragments.episodes.details.EpisodeDetailFragment
import com.example.rickandmorty.presentation.fragments.episodes.details.EpisodeDetailViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class EpisodesListFragment : Fragment(), EpisodesListAdapter.Listener {

	private lateinit var binding: FragmentEpisodesListBinding
	private val adapter = EpisodesListAdapter(this)
	private lateinit var viewModel: EpisodesListViewModel
	private val viewModelDetail: EpisodeDetailViewModel by activityViewModels()
	private lateinit var navigator: Navigator

	private var name = ""
	private var episode = ""

	@Inject
	lateinit var viewModelFactory: ViewModelFactory

	private val component by lazy {
		(requireActivity().application as App).component
	}

	override fun onAttach(context: Context) {
		component.inject(this)
		super.onAttach(context)
	}

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
		navigator.showBottomNav("Episodes")
		viewModel = ViewModelProvider(this, viewModelFactory)[EpisodesListViewModel::class.java]
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.episodeList.adapter = adapter
		binding.btnFilter.setOnClickListener {
			navigator.replaceFragment(
				EpisodeFilterFragment.newInstance(name, episode),
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

	override fun onClick(episode: EpisodeResult) {
		viewModelDetail.onClickItemEpisode(episode)
		navigator.replaceFragment(
			EpisodeDetailFragment(viewModelDetail),
		)
	}

	companion object {

		@JvmStatic
		fun newInstance() = EpisodesListFragment()
	}
}



