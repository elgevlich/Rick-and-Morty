package com.example.rickandmorty.presentation.fragments.locations

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
import com.example.rickandmorty.databinding.FragmentLocationsListBinding
import com.example.rickandmorty.domain.model.location.Location
import com.example.rickandmorty.presentation.Navigator
import com.example.rickandmorty.presentation.fragments.adapters.LocationAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class LocationsListFragment : Fragment(), LocationAdapter.Listener {

	private lateinit var binding: FragmentLocationsListBinding
	private lateinit var viewModel: LocationViewModel
	private lateinit var navigator: Navigator
	private val adapter = LocationAdapter(this)

	private var name = ""
	private var type = ""
	private var dimension = ""

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
		viewModel =
			ViewModelProvider(
				this,
				LocationViewModelFactory(RetrofitInstance.locationApi)
			)[LocationViewModel::class.java]
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.locationsList.adapter = adapter
		binding.btnFilter.setOnClickListener {
			navigator.replaceFragment(
				LocationFilterFragment.newInstance(name, type, dimension),
				"Filter"
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

	override fun onClick(location: Location) {
		viewModel.dataLocation.value = location
		navigator.replaceFragment(
			LocationDetailFragment.newInstance(
				location.name,
				location.type,
				location.dimension
			),
			"Location"
		)
	}

	companion object {

		@JvmStatic
		fun newInstance() = LocationsListFragment()
	}
}