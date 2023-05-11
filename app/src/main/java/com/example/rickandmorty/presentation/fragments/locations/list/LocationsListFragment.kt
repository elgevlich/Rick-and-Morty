package com.example.rickandmorty.presentation.fragments.locations.list

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
import com.example.rickandmorty.databinding.FragmentLocationsListBinding
import com.example.rickandmorty.di.ViewModelFactory
import com.example.rickandmorty.domain.model.location.LocationResult
import com.example.rickandmorty.presentation.Navigator
import com.example.rickandmorty.presentation.fragments.locations.LocationFilterFragment
import com.example.rickandmorty.presentation.fragments.locations.details.LocationDetailFragment
import com.example.rickandmorty.presentation.fragments.locations.details.LocationDetailViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class LocationsListFragment : Fragment(), LocationsListAdapter.Listener {

	private lateinit var binding: FragmentLocationsListBinding
	private lateinit var viewModel: LocationsListViewModel
	private val viewModelDetail: LocationDetailViewModel by activityViewModels()
	private lateinit var navigator: Navigator
	private val adapter = LocationsListAdapter(this)

	private var name = ""
	private var type = ""
	private var dimension = ""

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
			type = bundle.getString("type") ?: ""
			dimension = bundle.getString("dimension") ?: ""
			lifecycleScope.launch {
				viewModel.load(name, type, dimension)
				viewModel.locationFlow.collectLatest(adapter::submitData)
			}
		}
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentLocationsListBinding.inflate(inflater)
		navigator = requireActivity() as Navigator
		navigator.showBottomNav("Locations")
		viewModel = ViewModelProvider(this, viewModelFactory)[LocationsListViewModel::class.java]
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.locationsList.adapter = adapter
		binding.btnFilter.setOnClickListener {
			navigator.replaceFragment(
				LocationFilterFragment.newInstance(name, type, dimension),
			)
		}
		loadLocations()
		swipeRefresh()
	}

	private fun swipeRefresh() {
		binding.swipeRefresh.setOnRefreshListener {
			lifecycleScope.launch {
				adapter.submitData(PagingData.empty())
				viewModel.locationFlow.collectLatest(adapter::submitData)
			}
			binding.swipeRefresh.isRefreshing = false
		}
	}

	private fun loadLocations() {
		lifecycleScope.launch {
			viewModel.load(name, type, dimension)
			viewModel.locationFlow.collectLatest(adapter::submitData)
		}
		adapter.addLoadStateListener {
			binding.locationsList.isVisible = it.refresh != LoadState.Loading
			binding.progress.isVisible = it.refresh == LoadState.Loading
		}
	}

	override fun onClick(location: LocationResult) {
		viewModelDetail.onClickItemLocation(location)
		navigator.replaceFragment(
			LocationDetailFragment(
				viewModelDetail
			),
		)
	}

	companion object {

		@JvmStatic
		fun newInstance() = LocationsListFragment()
	}
}